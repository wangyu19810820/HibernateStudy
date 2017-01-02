package caveatemptor.converter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Currency;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.hibernate.usertype.CompositeUserType;
import org.hibernate.usertype.DynamicParameterizedType;

import caveatemptor.model.MonetaryAmount;

public class MonetaryAmountUserType implements CompositeUserType, DynamicParameterizedType {

	protected Currency convertTo;
	
	@Override
	// from DynamicParameterizedType
	public void setParameterValues(Properties parameters) {
//		ParameterType parameterType = (ParameterType)parameters.get(PARAMETER_TYPE);
//		String[] columns = parameterType.getColumns();
//		String table = parameterType.getTable();
//		Annotation[] annotations = parameterType.getAnnotationsMethod();
		
		String convertToParameter = parameters.getProperty("convertTo");
		this.convertTo = Currency.getInstance(
				convertToParameter != null ? convertToParameter : "USD"
		);
	}

	@Override
	// CompositeUserType, 从JDBC中获取值，转换成业务对象
	public Object nullSafeGet(ResultSet resultSet, String[] names, 
			SharedSessionContractImplementor session, Object owner)
			throws HibernateException, SQLException {
		BigDecimal amount = new BigDecimal(resultSet.getString(names[0]));
		if (resultSet.wasNull())
			return null;
		Currency currency = Currency.getInstance(resultSet.getString(names[1]));
		return new MonetaryAmount(amount, currency);
	}

	@Override
	// CompositeUserType, 业务对象存入数据库中,与nullSafeGet相对应
	public void nullSafeSet(PreparedStatement st, Object value, 
			int index, SharedSessionContractImplementor session)
			throws HibernateException, SQLException {
		if (value == null) {
			st.setNull(index, StandardBasicTypes.BIG_DECIMAL.sqlType());
			st.setNull(index + 1, StandardBasicTypes.BIG_DECIMAL.sqlType());
		} else {
			MonetaryAmount amount = (MonetaryAmount) value;
			BigDecimal newDecimal = amount.getValue();
			if (amount.getCurrency().toString().equals("USD")
					&& convertTo.toString().equals("EUR")) {
				newDecimal = newDecimal.divide(new BigDecimal("2"));
			} else if (amount.getCurrency().toString().equals("EUR")
					&& convertTo.toString().equals("USD")) {
				newDecimal = newDecimal.multiply(new BigDecimal("2"));
			}
			st.setBigDecimal(index, newDecimal);
			st.setString(index + 1, convertTo.getCurrencyCode());
		}
	}

	@Override
	// CompositeUserType,对象对应于数据库内的字段名称
	public String[] getPropertyNames() {
		return new String[]{"value", "currency"};
	}

	@Override
	// CompositeUserType,对象对应于数据库内的字段类型
	public Type[] getPropertyTypes() {
		return new Type[]{StandardBasicTypes.BIG_DECIMAL, StandardBasicTypes.CURRENCY};
	}

	@Override
	// CompositeUserType,获取值
	public Object getPropertyValue(Object o, int index) throws HibernateException {
		MonetaryAmount amount = (MonetaryAmount)o;
		if (index == 0)
			return amount.getValue();
		else
			return amount.getCurrency();
	}

	@Override
	// CompositeUserType,设置值
	public void setPropertyValue(Object o, int index, Object value) throws HibernateException {
		throw new UnsupportedOperationException("MonetaryAmount is immutable");
	}

	// 以下代码都是脚手架代码
	@Override
	// CompositeUserType,适配指定的类
	public Class returnedClass() {
		return MonetaryAmount.class;
	}

	@Override
	// CompositeUserType，不可变，Hibernate可优化
	public boolean isMutable() {
		return false;
	}

	@Override
	// CompositeUserType，值类型可以直接返回，否则需要写代码实现
	public Object deepCopy(Object value) throws HibernateException {
		// 因为是值类型
		return value;
	}
	
	@Override
	// CompositeUserType，全局共享二级缓存，会需要序列化，也可以直接返回对象本身
	public Serializable disassemble(Object value, 
			SharedSessionContractImplementor session) throws HibernateException {
		return value.toString();
	}

	@Override
	// CompositeUserType，全局共享二级缓存，会需要序列化，和disassemble配对使用
	public Object assemble(Serializable cached, SharedSessionContractImplementor session, Object owner)
			throws HibernateException {
		return MonetaryAmount.fromString((String)cached);
	}

	@Override
	// CompositeUserType,EntityManager#merge()操作期间调用
	public Object replace(Object original, Object target, 
			SharedSessionContractImplementor session, Object owner)
			throws HibernateException {
		return original;
	}

	@Override
	// CompositeUserType,判断是否相等，以决定数据库是否需要更新
	public boolean equals(Object x, Object y) throws HibernateException {
		if (x == y)
			return true;
		else if (x == null)
			return y == null;
		else
			return x.equals(y);
	}

	@Override
	// CompositeUserType
	public int hashCode(Object x) throws HibernateException {
		return x.hashCode();
	}

}
