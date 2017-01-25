@FilterDefs({
	@FilterDef(
			name = "limitByUserRank",
			parameters = {
				@ParamDef(
					name = "currentUserRank",
					type = "int"
				)
			},
			defaultCondition =
				":currentUserRank <= (select u.rank from ce_user u where u.id = seller_id)"
		),
	@FilterDef(
			name = "limitByUserRank1",
			parameters = {
				@ParamDef(
					name = "currentUserRank",
					type = "int"
				)
			},
			defaultCondition =
				":currentUserRank >= (select u.rank from ce_user u where u.id = seller_id)"
		)
		
})

package filtering;

import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;
