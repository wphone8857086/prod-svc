package com.jt.plt.product.entity.calPremium;


/**
 * 
 * 描述：保费计算公式格式定义校验 
 * 类名称：Formula.java 
 * 作者： wephone 
 * 创建日期： 2018年1月29日 下午3:46:10
 * 版权：江泰保险经纪股份有限公司
 */
public class FormulaVerify {
	private int leftBracket = 0;// 左括号个数
	private int rightBracket = 0;// 右括号个数
	private int startL = 0;// 左括号的位置
	private int startR = 0;// 右括号的位置
	public static String Msg = "";
	private String formula = "";

	/*
	 * 构造方法，以要算的公式为参数
	 */
	public FormulaVerify(String calRule) {
		this.setFormula(calRule);
	}

	/*
	 * 获得左括号数
	 */
	private int getLeftBracket(String calRule) {
		leftBracket = 0;
		startL = calRule.indexOf("(");
		if (startL != -1) {
			calRule = calRule.substring(startL + 1, calRule.length());
		}
		while (startL != -1) {
			leftBracket++;
			startL = calRule.indexOf("(");
			calRule = calRule.substring(startL + 1, calRule.length());
		}
		return leftBracket;
	}

	/*
	 * 设置格式
	 */
	public void setFormula(String calRule) {
		formula = replaceSubtration(calRule.trim());
		formula = "(" + formula + ")";
	}

	/*
	 * 为了使公式中支持负数，使用“`”表示减号，使用“-”表示负号，把所有减号换成“‘”
	 */
	private String replaceSubtration(String vstr) {
		String tmp = "";
		String result = "";
		int startS = vstr.indexOf("-");
		if (startS != -1) {
			if (startS > 0) {
				tmp = vstr.substring(startS - 1, startS);
				if (!"+".equals(tmp) && !"-".equals(tmp) && !"*".equals(tmp) && !"/".equals(tmp) && !"(".equals(tmp)) {
					result = result + vstr.substring(0, startS) + "`";
				} else {
					result = result + vstr.substring(0, startS + 1);
				}
			} else {
				result = result + vstr.substring(0, startS + 1);
				vstr = vstr.substring(startS + 1);
			}
		}
		while (startS != -1) {
			startS = vstr.indexOf("-");
			if (startS > 0) {
				tmp = vstr.substring(startS - 1, startS);
				if (!"+".equals(tmp) && !"-".equals(tmp) && !"*".equals(tmp) && !"/".equals(tmp) && !"(".equals(tmp)) {
					result = result + vstr.substring(0, startS) + "`";
				}else {
					result = result + vstr.substring(0, startS + 1);
				}
			} else {
				result = result + vstr.substring(0, startS + 1);
				vstr = vstr.substring(startS + 1);
			}
		}
		result += vstr;
		return result;
	}

	public String getFormula() {
		return formula.replace('`', '-').substring(1, formula.length() - 1);
	}

	/*
	 * 获得右括号数
	 */
	private int getRightBracket(String calRule) {
		rightBracket = 0;
		startR = calRule.indexOf(")");
		if (startR != -1) {
			calRule = calRule.substring(startR + 1, calRule.length());
		}
		while (startR != -1) {
			rightBracket++;
			startR = calRule.indexOf(")");
			calRule = calRule.substring(startR + 1, calRule.length());
		}
		return rightBracket;
	}

	/*
	 * /*对比左右括号个数
	 */
	private boolean compareToLR() {
		int lb = getLeftBracket(formula);
		int rb = getRightBracket(formula);
		boolean ctlr = false;
		if (lb == rb) {
			Msg = "";
			ctlr = true;
		} else if (lb > rb) {
			Msg = "左括弧的个数多于右括弧,请检查!";
			ctlr = false;
		} else {
			Msg = "左括弧的个数少于右括弧,请检查!";
			ctlr = false;
		}
		return ctlr;
	}

	/*
	 * /*检查公式中是否存在非法字符如(+、-)等
	 */
	private boolean checkFormula() {
		boolean isOk = true;
		String[] bracket = new String[2];
		String[] sign = new String[4];
		bracket[0] = "(";
		bracket[1] = ")";
		sign[0] = "+";
		sign[1] = "`";
		sign[2] = "*";
		sign[3] = "/";
		String vstr = "";
		for (int i = 0; i < bracket.length; i++) {
			for (int j = 0; j < sign.length; j++) {
				if (i == 0) {
					vstr = bracket[i] + sign[j];
				}else {
					vstr = sign[j] + bracket[i];
				}if (formula.indexOf(vstr) > 0) {
					Msg = "公式中存在非法字符" + vstr;
					isOk = false;
					return isOk;
				}
			}
		}
		for (int i = 0; i < sign.length; i++) {
			for (int j = 0; j < sign.length; j++) {
				vstr = sign[i] + sign[j];
				if (formula.indexOf(vstr) > 0) {
					Msg = "公式中存在非法字符" + vstr;
					isOk = false;
					return isOk;
				}
			}
		}
		if (formula.indexOf("()") > 0) {
			Msg = "公式中存在非法字符()";
			isOk = false;
		}
		return isOk;
	}

	/*
	 * 判断输入的字符串是否合法
	 */
	public boolean checkValid() {
		if ((formula == null) || (formula.trim().length() <= 0)) {
			Msg = "请设置属性calRule!";
			return false;
		}
		return (compareToLR() && checkFormula());
	}

	public boolean containsLetter(String insStr) {
		boolean isLetter = false;

		for (int i = 0; i < insStr.length(); i++) {
			char ch = insStr.charAt(i);
			if (Character.isLetter(ch)) {
				isLetter = true;
			}
		}
		return isLetter;
	}

	public Double parseLetter(String insStr, Double para) {

		if (insStr.charAt(0) == '-') {
			para = 0 - para;
		}
		return para;
	}

	

	public void testFormula() {
		/*String str = "{\r\n" + "\"formula\":\"a*(1+b)+c*(1+d)\",\r\n" + "\"data\":{\r\n" + "\"a\":5000.00,\r\n"
				+ "\"b\":0.2,\r\n" + "\"c\":0.3,\r\n" + "\"d\":0.1\r\n" + "}\r\n" + "}";
		JSONObject json = JSONObject.fromObject(str);
		String formula = (String) json.get("formula");
		JSONObject obj = (JSONObject) json.get("data");
		Object a = obj.get("a");
		Object b = obj.get("b");
		Object c = obj.get("c");
		Object d = obj.get("d");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("a", a);
		map.put("b", b);
		map.put("c", c);
		map.put("d", d);
		Object convertToCode = FormulaVerify.convertToCode(formula, map);
		System.out.println(convertToCode);*/
	}
	public static void main(String[] args) {
		FormulaVerify test2 = new FormulaVerify("(a`*b+c*d)");
		boolean b = test2.checkValid();
		if (!b == true) {
			System.out.println(Msg);
		}
	}
}