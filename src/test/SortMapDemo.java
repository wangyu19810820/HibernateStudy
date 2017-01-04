package test;

import java.util.Comparator;
import java.util.SortedMap;
import java.util.TreeMap;

public class SortMapDemo {

	public static void main(String[] args) {
		TreeMap<String, String> map = new TreeMap<String, String>(new ReverseStringComparator());
		map.put("file1.jpg", "file1");
		map.put("file3.jpg", "file3");
		map.put("file2.jpg", "file2");
		System.out.println(map);
	}

	public static class ReverseStringComparator implements Comparator<String> {
	    @Override
	    public int compare(String a, String b) {
	        return b.compareTo(a);
	    }
	}
}
