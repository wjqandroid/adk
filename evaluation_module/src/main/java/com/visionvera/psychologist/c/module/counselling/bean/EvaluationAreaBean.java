package com.visionvera.psychologist.c.module.counselling.bean;

import java.util.List;

/**
 * @Desc 地区选择实体类
 * @Author yemh
 * @Date 2019-12-24 15:46
 */
public class EvaluationAreaBean {

    private List<ProvinceBean> result;

    public List<ProvinceBean> getResult() {
        return result;
    }

    public void setResult(List<ProvinceBean> result) {
        this.result = result;
    }

    public static class ProvinceBean {
        private String code;
        private int id;
        private int level;
        private String mergerName;
        private String name;
        private boolean select = false;
        private List<CityBean> result;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getMergerName() {
            return mergerName;
        }

        public void setMergerName(String mergerName) {
            this.mergerName = mergerName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isSelect() {
            return select;
        }

        public void setSelect(boolean select) {
            this.select = select;
        }

        public List<CityBean> getResult() {
            return result;
        }

        public void setResult(List<CityBean> result) {
            this.result = result;
        }

        public static class CityBean {
            private String code;
            private int id;
            private int level;
            private String mergerName;
            private String name;

            private boolean select = false;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getMergerName() {
                return mergerName;
            }

            public void setMergerName(String mergerName) {
                this.mergerName = mergerName;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public boolean isSelect() {
                return select;
            }

            public void setSelect(boolean select) {
                this.select = select;
            }
        }
    }
}
