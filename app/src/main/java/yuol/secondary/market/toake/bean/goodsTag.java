package yuol.secondary.market.toake.bean;

import java.util.List;

public class goodsTag {

    /**
     * code : 200
     * message : 栏目页数据获取成功
     * data : [{"cat_id":"22","catname":"二手书籍","num":"0"},{"cat_id":"21","catname":"鞋服配饰","num":"6"},{"cat_id":"19","catname":"特长爱好","num":"0"},{"cat_id":"18","catname":"家具日用","num":"0"},{"cat_id":"17","catname":"闲置数码","num":"0"},{"cat_id":"8","catname":"其他商品","num":"0"},{"cat_id":"7","catname":"学长学姐笔记","num":"0"},{"cat_id":"6","catname":"电子产品","num":"0"},{"cat_id":"3","catname":"二手书籍","num":"0"}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * cat_id : 22
         * catname : 二手书籍
         * num : 0
         */

        private String cat_id;
        private String catname;
        private String num;

        public String getCat_id() {
            return cat_id;
        }

        public void setCat_id(String cat_id) {
            this.cat_id = cat_id;
        }

        public String getCatname() {
            return catname;
        }

        public void setCatname(String catname) {
            this.catname = catname;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }
    }
}
