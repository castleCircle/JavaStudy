package inflearn.item1.flyweight;

import java.util.HashMap;
import java.util.Map;

public class FontFactory {

  private static final Map<String,Font> fontMap = new HashMap();

  public static Font getFont(String font){

    if(fontMap.containsKey(font)){
      return fontMap.get(font);
    }else{
      Font newFont = new Font(font,1);
      fontMap.put(font,newFont);
      return newFont;
    }

  }

}
