package utils;

import java.util.Random;

public class ColorResource {
	
	private static Color generateRandomColor() {
       
		Random randColor = new Random();   
        int r = randColor.nextInt(256);  
        int g = randColor.nextInt(256);  
        int b = randColor.nextInt(256);  
       
        return new Color(r, g, b);
	}
	
	private static String exportToHexString(String hexString) {
		
		String hex = null;  
    	if(hexString.length() == 1) hex = '0'+hexString;  
    	else  						hex = hexString;  
    	  
    	return hex;
	}
	
	public static String genarateHexadecimalColor(){  
		
		Color color = generateRandomColor();
		
		return '#'+  
				exportToHexString(Integer.toHexString(color.getRed()))+  
				exportToHexString(Integer.toHexString(color.getGreen()))+  
				exportToHexString(Integer.toHexString(color.getBlue()));  
	}  
}
