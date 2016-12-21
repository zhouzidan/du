package du.zhou.com.du.common;

/**
 * Created by zhou on 2016/12/21.
 */

public class Contants {
    public enum ProductDetailType {
        TYPE_IMG("11");
        String value;

        private ProductDetailType(String value) {
            this.value = value;
        }

        public String getValue(){
            return this.value;
        }
    }
}
