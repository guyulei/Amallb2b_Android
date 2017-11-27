package com.amall360.amallb2b_android.bean;

import java.util.List;

/**
 * Created by A on 2017/11/27.
 */

public class DomainListBean {

    /**
     * message : 获取成功
     * status_code : 200
     * data : {"list":[{"id":"1","name":"北京市"},{"id":"9","name":"上海市"},{"id":"11","name":"浙江省"}],"site":{"start":-1,"end":-1,"country":"中国","province":"浙江","city":"杭州","district":"","isp":"","type":"","desc":"","ip":"125.118.127.162","id":"1"}}
     */

    private String   message;
    private int      status_code;
    private DataBean data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * list : [{"id":"1","name":"北京市"},{"id":"9","name":"上海市"},{"id":"11","name":"浙江省"}]
         * site : {"start":-1,"end":-1,"country":"中国","province":"浙江","city":"杭州","district":"","isp":"","type":"","desc":"","ip":"125.118.127.162","id":"1"}
         */

        private SiteBean       site;
        private List<ListBean> list;

        public SiteBean getSite() {
            return site;
        }

        public void setSite(SiteBean site) {
            this.site = site;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class SiteBean {
            /**
             * start : -1
             * end : -1
             * country : 中国
             * province : 浙江
             * city : 杭州
             * district :
             * isp :
             * type :
             * desc :
             * ip : 125.118.127.162
             * id : 1
             */

            private int    start;
            private int    end;
            private String country;
            private String province;
            private String city;
            private String district;
            private String isp;
            private String type;
            private String desc;
            private String ip;
            private String id;

            public int getStart() {
                return start;
            }

            public void setStart(int start) {
                this.start = start;
            }

            public int getEnd() {
                return end;
            }

            public void setEnd(int end) {
                this.end = end;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
            }

            public String getIsp() {
                return isp;
            }

            public void setIsp(String isp) {
                this.isp = isp;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getIp() {
                return ip;
            }

            public void setIp(String ip) {
                this.ip = ip;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }

        public static class ListBean {
            /**
             * id : 1
             * name : 北京市
             */

            private String id;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
