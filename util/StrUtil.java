package com.haier.neusoft.o2o.common.util;


public class StrUtil {
    //hzj修改sonar违规，没有用到全部注释。20150326
//	    private static final String REGEX_SCRIPT = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
//	    private static final String REGEX_STYLE = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
//	    private static final String REGEX_HTML = "<[^>]+>"; // 定义HTML标签的正则表达式
//	    private static final String REGEX_SPACE = "\\s*|\t|\r|\n";//定义空格回车换行符
	
//	public static String createUUID() {
//
//		UUID uuid = UUID.randomUUID();
//		String s = uuid.toString();
//		return s;
//	}
//
//	public static boolean isSameStrSet(Set<String> set1, Set<String> set2) {
//		boolean isSame = true;
//		if (set1 != null && set2 != null) {
//
//			if (set1.size() != set2.size()) {
//				isSame = false;
//			} else {
//				for (String tmpStr: set1) {
//					if (!set2.contains(tmpStr)) {
//						isSame = false;
//						break;
//					}
//				}
//			}
//
//		} else if (set1 == null && set2 == null) {
//			//hzj修改sonar违规，该if语句中逻辑为空，看方法逻辑，应该是在该种情况下返回true 20150326
//            isSame = true;
//		} else {
//			isSame = false;
//		}
//		return	isSame;
//	}
//
//	public static int	compareIgnoreNull(String s1, String s2) {
//		if (s1 == null) {
//			if (s2 == null) {
//				return 0;
//            }else {
//                return -1;
//            }
//		} else {
//			if (s2 == null){
//                return	1;
//            } else{
//                return	s1.compareTo(s2);
//            }
//		}
//	}
//	public static String strToUpperCase(String str){
//		String result=null;
//	if (str != null) {
//			result = str.toUpperCase();
//		}
//	return result;
//	}
//	public static String strToLowerCase(String str){
//		String result=null;
//	if (str != null) {
//			result = str.toLowerCase();
//		}
//	return result;
//	}
//
//
//	/***************************
//	 * 判断是否是手机号码
//	 *
//	 * @param str
//	 * @return
//	 */
//	public static boolean isPhoneNumber(String str) {
//		boolean res = false;
//		if (StringUtils.isNotBlank(str)) {
//
//			Pattern p = Pattern.compile("^((13[0-9])|(14[0-9])|(15[0-9])|(18[0-9]))\\d{8}$");
//			Matcher m = p.matcher(str);
//			return m.matches();
//		}
//		return	res;
//	}
//
//
//
//	/************
//	 * 过滤医嘱号为数字：
//	 * 目前组号形式R00001,S000001,以后组号修改后也需要变化：
//	 * @param str,allowPrefixZero
//	 * @return
//	 */
//	public static String filterUnNumber(String str, boolean allowPrefixZero) {
//        // 只允数字
//		String	res = null;
//		if (StringUtils.isNotBlank(str)) {
//	        String regEx = "[^0-9]";
//	        Pattern p = Pattern.compile(regEx);
//	        Matcher m = p.matcher(str);
//	        //替换与模式匹配的所有字符（即非数字的字符将被""替换）
//	        res = m.replaceAll("").trim();
//		}
//		if (!allowPrefixZero && StringUtils.isNotBlank(res) &&  StringUtils.isNumeric(res)) {
//			res = String.valueOf(Long.valueOf(res));
//		}
//		if (StringUtils.isBlank(res)) {
//			res = ""; // 必须返回空或数字, HuiTong同步需要数字
//		}
//		return res;
//	}
//
//	/**
//	 * 利用MD5进行加密
//     * @param str  待加密的字符串
//     * @return  加密后的字符串
//     * @throws java.security.NoSuchAlgorithmException  没有这种产生消息摘要的算法
//     * @throws java.io.UnsupportedEncodingException
//     */
//    public static String encoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
//    	if(StringUtils.isEmpty(str)){
//    		return null;
//    	}
//        //确定计算方法
//        MessageDigest md5=MessageDigest.getInstance("MD5");
//        BASE64Encoder base64en = new BASE64Encoder();
//        //加密后的字符串
//        String newstr=base64en.encode(md5.digest(str.getBytes("utf-8")));
//        return newstr;
//    }
//
//
//
//
//
//    public static String getEndCharacter(String str) {
//		if (StringUtils.isNotBlank(str) && str.length() >=1) {
//			return str.substring(str.length()-1);
//		} else {
//			return "";
//		}
//	}
//
//
//    public static String	formatMessageToMultiLineForErrorMsg(String msg, String sChar) {
//    	StringBuffer	ret = new StringBuffer();
//        ret.append(msg);
//    	if (StringUtils.isNotBlank(msg) && StringUtils.isNotBlank(sChar)) {
//    		String[] strs = StringUtils.split(msg, sChar);
//    		if (strs!= null && strs.length >0) {
//    			ret.delete(0,ret.length());
//    			for (String tmpStr: strs) {
//    				if (StringUtils.isNotBlank(tmpStr.trim())) {
//    					ret.append(tmpStr.trim()).append("\n");
//    				}
//    			}
//    		}
//    	}
//    	return	ret.toString();
//    }
//
//    public static List<String>  splitQuickSearchString(String searchStr) {
//    	List<String> retList = new ArrayList<String>();
//    	String[] tmpArray = StringUtils.split(searchStr, " ");
//    	if (tmpArray != null) {
//    		for (String tmpStr: tmpArray) {
//    			if (StringUtils.isNotBlank( StringUtils.stripToEmpty(tmpStr)) ) {
//    				retList.add(StringUtils.stripToEmpty(tmpStr));
//    			}
//    		}
//    	}
//    	return retList;
//    }
//
//    public static List<String>  splitString(String searchStr,String ch) {
//    	List<String> retList = new ArrayList<String>();
//    	String[] tmpArray = StringUtils.split(searchStr, ch);
//    	if (tmpArray != null) {
//    		for (String tmpStr: tmpArray) {
//    			if (StringUtils.isNotBlank( StringUtils.stripToEmpty(tmpStr)) ) {
//    				retList.add(StringUtils.stripToEmpty(tmpStr));
//    			}
//    		}
//    	}
//    	return retList;
//    }
//
//
//
//    public static String	strToFixLength(String str, String appendChar, int len) {
//    	String	ret = str;
//    	if (ret == null) {
//    		ret = "";
//    	}
//    	//过长，取右边
//    	if (ret.length() > len) {
//    		ret = ret.substring(ret.length() - len);
//    	}
//    	//过短，补左边
//    	if (ret.length() < len) {
//    		for (int i=0; i < len - ret.length(); i++) {
//    			ret = appendChar + ret;
//    		}
//    	}
//    	return	ret;
//    }
//
//
//    public static String	strToMaxLength(String str, int len) {
//    	String	ret = str;
//    	if (ret == null) {
//    		ret = "";
//    	}
//    	//过长，取右边
//    	if (ret.length() > len) {
//    		ret = ret.substring(ret.length() - len);
//    	}
//    	return	ret;
//    }
//
//    public static String  replaceStringDesc(String originalDesc, String findStr, String replaceStr, boolean isCaseSensitive) {
//    	String newStr = originalDesc;
//    	if (StringUtils.isNotBlank(originalDesc) && StringUtils.isNotBlank(findStr)
//    			&& StringUtils.isNotBlank(replaceStr)) {
//	    	if (isCaseSensitive) {
//				if (originalDesc.contains(findStr)) {
//					newStr = originalDesc.replace(findStr, replaceStr);
//				}
//			} else {
//				String upperStr = StringUtils.upperCase(originalDesc);
//				String expr = "(?i)" + StringUtils.trim(findStr);
//				if (upperStr.indexOf(StringUtils.upperCase(findStr)) >=0 ) {
//					newStr = originalDesc.replaceAll(expr, replaceStr);
//				}
//			}
//    	}
//    	return newStr;
//    }
//
//    public static String formatAlertMessageInDb(String msgCode, String key) {
//    	String res = "[" + msgCode + "]" + key;
//    	return res;
//    }
//    public static String object2String(Object obj){
//		if(obj==null){
//			return "";
//		}
//		else{
//			return String.valueOf(obj);
//		}
//	}
//    public static String replaceZero(String source){
//    	if(source==null){
//			return "";
//		}
//		else{
//			String newStr = source.replaceAll(".000000", "");
//			return newStr;
//		}
//
//    }
//    public static String stackEmptyStr(Object obj){
//    	if(obj==null){
//			return null;
//		}
//		else{
//
//			return String.valueOf(obj);
//		}
//    }
    
//
//    public static HashMap<String, String>  parseParameterForMultiValuePair(String parameterValue) {
//		HashMap<String, String>  resMap = new HashMap<String,String>();
//
//		if (StringUtils.isNotBlank(parameterValue)) {
//
//			String[]	paraArray = parameterValue.split("[" + GlobalConstant.SYSTEM_PARAMETER_SPLIT_CHAR + "]");
//			if (paraArray != null && paraArray.length > 0) {
//				for (int i=0; i< paraArray.length; i++) {
//					String para = paraArray[i], code = "", value = "";
//					int	pos = para.indexOf(GlobalConstant.SYSTEM_PARAMETER_SPLIT_CHAR_FOR_VALUE);
//					if (pos >=0) {
//						code = para.substring(0, pos);
//						if (pos < para.length())
//							value = para.substring(pos + 1);
//						else
//							value = "";
//					} else {
//						code = para;
//						value = para;
//					}
//					resMap.put(code, value);
//				}
//			}
//		}
//		return	resMap;
//	}
//
//    public static String buildParameterForMultiValuePair(HashMap<String, String> valueMap) {
//		StringBuffer  sbuf = new StringBuffer();
//		if (valueMap != null && valueMap.size() > 0) {
//			Iterator<String>	ite = valueMap.keySet().iterator();
//			while (ite.hasNext()) {
//				String	code = ite.next();
//				String	value = (String)valueMap.get(code);
//
//				if (sbuf.length() != 0 ) {
//					sbuf.append(GlobalConstant.SYSTEM_PARAMETER_SPLIT_CHAR);
//				}
//				sbuf.append(code);
//				sbuf.append(GlobalConstant.SYSTEM_PARAMETER_SPLIT_CHAR_FOR_VALUE);
//				sbuf.append(value);
//			}
//		}
//		return	sbuf.toString();
//	}
    
//    public static boolean isPhoneNum(String str){
//    	String regex = "^[1][3458][0-9]{9}$";
//    	return str.matches(regex);
//    }
//
//    public static String	removeLastCharFromString(String code, String suffix) {
//    	String	res = code;
//    	if (code != null) {
//    		while (res.length() > 1 && StringUtils.endsWith(res, suffix) ) {
//    			res = res.substring(0, res.length()-1);
//    		}
//    	}
//    	return res;
//    }
//    /**
//     * 将一个字符串数组转换为一个含指定分隔符的字符串
//     * @param arr
//     * @param separator	元素之间的间隔符,可为null(将作空字符串处理)
//     * @param start	前缀符号,可为null(将作空字符串处理)
//     * @param end	后缀符号,可为null(将作空字符串处理)
//     * @return	若数组参数为null，将返回null
//     */
//    public static String transArrayToString(String[]arr,String separator,String start,String end){
//    	//hzj 20150326 修改sonar违规，不能对参数进行赋值
//        String sepa = "";
//        if(separator != null){
//            sepa = separator;
//        }
//        String starts = "";
//    	if(start != null){
//            starts = start;
//        }
//        String ends = "";
//    	if(end != null){
//            ends=end;
//        }
//    	if(arr==null) {
//            return null;
//        }
//
//    	StringBuffer result=new StringBuffer(starts);
//
//    	boolean isFirst = true;
//    	for (int i = 0; i < arr.length; i++) {
//    		if (arr[i]==null) {
//    			arr[i]="";
//    		}
//    		if (isFirst) {
//				result.append(arr[i]);
//				isFirst = false;
//			} else {
//                String ss = sepa+arr[i];
//				result.append(ss);
//			}
//		}
//    	result.append(ends);
//    	return result.toString();
//    }
//    public static String getDecimalFormatStr(Double d){
//    	String result="";
//    	if(d!=null){
//	    	java.text.DecimalFormat df =new java.text.DecimalFormat("##.00");
//	    	 result=df.format(d);
//
//    	}
//    	return result;
//    }
//
//    public static String mergeListToOneString(List<String> names, String splitChar) {
//        StringBuffer res = new StringBuffer();
//    	if (names != null && names.size() >0) {
//    		for (String s: names) {
//                res.append(s).append(splitChar);
//    		}
//    	}
//    	return StringUtils.stripToEmpty(res.toString());
//    }
//    public static String subZeroAndDot(String s){
//        String newStr = "";
//        //修改sonar违规，单字符使用 char类型  indexof速度会更快   update hzj 20150326
//        if(s.indexOf('.') > 0){
//            newStr = s.replaceAll("0+?$", "");//去掉多余的0
//            newStr = s.replaceAll("[.]$", "");//如最后一位是.则去掉
//        }
//        return newStr;
//    }
//    public static String replacePreZero(String s){
//    	String newStr="";
//    	if(StringUtils.isNotBlank(s)){
//    		 newStr = s.replaceAll("^(0+)", "");
//
//    	}
//    	return newStr;
//    }
//
//    public static Double strToDouble(String s){
//    	Double res=0D;
//    	if(org.apache.commons.lang.StringUtils.isNotBlank(s)){
//    	try{
//             double d = (new Double(s)).doubleValue();
//             BigDecimal df = new BigDecimal(d);
//             double rate2 = df.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//             res=rate2;
//         }
//         catch(NumberFormatException e){
//
//         }
//    	}
//    	return res;
//    }
//    public static Double rateDouble(Double d,Integer number){
//    	Double res=0D;
//
//    	if(d!=null){
//    		BigDecimal df = new BigDecimal(d);
//            double rate2 = df.setScale(number, BigDecimal.ROUND_HALF_UP).doubleValue();
//    		res=rate2;
//
//    	}
//    	return res;
//    }
//    /**
//     * 解码
//     * @param str
//     * @return string
//     */
//	@SuppressWarnings("restriction")
//	public static byte[] decode(String str)throws Exception{
//	    byte[] bt = null;
//        sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
//        bt = decoder.decodeBuffer( str );
//	    return bt;
//    }
//	public static String replaceBlank(String source)
//	 {
//	  String after="";
//	  Pattern p = Pattern.compile("//s*|/t|/r|/n");
//	  Matcher m = p.matcher(source);
//	  after = m.replaceAll("");
//	  return after;
//
//	 }
//	public static String subTooLongString(String sourStr,int maxSize){
//		String res="";
//		if(org.apache.commons.lang.StringUtils.isNotBlank(sourStr)){
//			if(sourStr.length()>maxSize){
//				res=sourStr.substring(0, maxSize);
//			}else{
//				res=sourStr;
//			}
//		}
//		return res;
//	}
	
	public static String encodeOrderId(String orderId) throws  Exception{
		
		String encodeStr = null;
        //将订单ID, 手机号转化为固定长度的64进制数字
        String  idHex = HexUtil.compressNumber(Long.valueOf(orderId), HexUtil.HEX_SHIFT);
        encodeStr = idHex ;
		return encodeStr;
	}
	
	public static String decodeOrderId(String encodeStr)throws  Exception {
		String result = null;
        String elem1 = encodeStr.substring(0, encodeStr.length());
        Long	orderNum = HexUtil.unCompressNumber(elem1);
        result=orderNum.toString();
		return result;
	}
}
