package com.jt.plt.product.util;

/**
 * @Description: 
 * @author:
 * @Date: 2018/7/21 15:47
 * @ModifiedDate：
 * @Copyright:江泰保险股份有限公司 
 */
public class MapUtil {

    public MapUtil() {
    }

/*    public static final <KT, VT> Map<KT, VT> buildMapKVGen(Object[][] arr, Class<KT> kclass, Class<VT> vclass) {
        if (arr != null && arr.length != 0) {
            Map<KT, VT> res = new HashMap();
            Object[][] var4 = arr;
            int var5 = arr.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                Object[] row = var4[var6];
                res.put(row[0], row[1]);
            }

            return res;
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        String[][] arr = new String[][]{{"a", "a1"}, {"b", "b1"}};
        Map<String, String> res = buildMapKVGen(arr, String.class, String.class);
        System.out.println((String)res.get("a"));
        Object[][] arr2 = new Object[][]{{Integer.valueOf(1), "a1"}, {Integer.valueOf(2), Integer.valueOf(3)}};
        Map<Integer, Object> res2 = buildMapKVGen(arr2, Integer.class, Object.class);
        System.out.println(res2.get(Integer.valueOf(1)));
    }*/
}
