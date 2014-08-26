
public abstract class Key {
	
	public static String generateKey(String ip, int port) {
		int key = 0;
		String strKey = null;
		try {
			if(!ip.substring(0,8).equals("192.168.")) {
				TicTacToe.error(new Exception());
			} else {
				boolean makeNegative = false;
				String firstpart = ip.substring(8,9);
				if(firstpart.equals("0")) {
					makeNegative = true;
				} else {
					int toBeAdded = Integer.parseInt(firstpart);
					for(int i = 0; i < ip.substring(10).length(); i++) {
						toBeAdded*=10;
					}
					key += toBeAdded;
				}
				key += Integer.parseInt(ip.substring(10));
				StringBuffer strBfrKey = new StringBuffer(String.valueOf(key));
				strBfrKey.append(".");
				strBfrKey.append(port);
				if(makeNegative) strBfrKey.insert(0, "-");
				strKey = String.valueOf(strBfrKey);
			}
		} catch (Exception e) {
			System.out.println("There was an error creating a key!");
			TicTacToe.error(e);
		}
		return strKey;
	}
	
	public static Object[] parseKey(String key) {
		Object returnVal[] = { 0, "" };
		StringBuffer generatedIp = new StringBuffer("");
		int generatedPort = 0;
		generatedIp.append("192.168.");
		if(key.substring(0,1).equals("-")) {
			generatedIp.append("0");
		} else {
			generatedIp.append(key.substring(0,1));
		}
		key = key.substring(1);
		String[] sides = key.split("[.]");
		generatedIp.append(".");
		generatedIp.append(sides[0]);
		generatedPort = Integer.parseInt(sides[1]);
		returnVal[0] = String.valueOf(generatedIp);
		returnVal[1] = generatedPort;
		return returnVal;
	}
}
