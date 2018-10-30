package yuol.secondary.market.toake.bean;

import java.util.List;

public class ImageUrl {


    /**
     * info : 图片名查询成功
     * start : http://192.168.137.1/taoke/1.jpg
     * banner : [{"banner":"http://192.168.137.1/taoke/1.jpg"},{"banner":"http://192.168.137.1/taoke/2.jpg"},{"banner":"http://192.168.137.1/taoke/3.png"},{"banner":"http://192.168.137.1/taoke/4.png"},{"banner":"http://192.168.137.1/taoke/5.jpg"}]
     */

    private String info;
    private String start;
    private List<BannerBean> banner;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public static class BannerBean {
        /**
         * banner : http://192.168.137.1/taoke/1.jpg
         */

        private String banner;

        public String getBanner() {
            return banner;
        }

        public void setBanner(String banner) {
            this.banner = banner;
        }
    }
}
