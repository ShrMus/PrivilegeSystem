package com.shrmus.pojo;

import java.util.ArrayList;
import java.util.List;

public class PrivilegeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PrivilegeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andPrivilegeIdIsNull() {
            addCriterion("privilege_id is null");
            return (Criteria) this;
        }

        public Criteria andPrivilegeIdIsNotNull() {
            addCriterion("privilege_id is not null");
            return (Criteria) this;
        }

        public Criteria andPrivilegeIdEqualTo(Integer value) {
            addCriterion("privilege_id =", value, "privilegeId");
            return (Criteria) this;
        }

        public Criteria andPrivilegeIdNotEqualTo(Integer value) {
            addCriterion("privilege_id <>", value, "privilegeId");
            return (Criteria) this;
        }

        public Criteria andPrivilegeIdGreaterThan(Integer value) {
            addCriterion("privilege_id >", value, "privilegeId");
            return (Criteria) this;
        }

        public Criteria andPrivilegeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("privilege_id >=", value, "privilegeId");
            return (Criteria) this;
        }

        public Criteria andPrivilegeIdLessThan(Integer value) {
            addCriterion("privilege_id <", value, "privilegeId");
            return (Criteria) this;
        }

        public Criteria andPrivilegeIdLessThanOrEqualTo(Integer value) {
            addCriterion("privilege_id <=", value, "privilegeId");
            return (Criteria) this;
        }

        public Criteria andPrivilegeIdIn(List<Integer> values) {
            addCriterion("privilege_id in", values, "privilegeId");
            return (Criteria) this;
        }

        public Criteria andPrivilegeIdNotIn(List<Integer> values) {
            addCriterion("privilege_id not in", values, "privilegeId");
            return (Criteria) this;
        }

        public Criteria andPrivilegeIdBetween(Integer value1, Integer value2) {
            addCriterion("privilege_id between", value1, value2, "privilegeId");
            return (Criteria) this;
        }

        public Criteria andPrivilegeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("privilege_id not between", value1, value2, "privilegeId");
            return (Criteria) this;
        }

        public Criteria andPrivilegeNameIsNull() {
            addCriterion("privilege_name is null");
            return (Criteria) this;
        }

        public Criteria andPrivilegeNameIsNotNull() {
            addCriterion("privilege_name is not null");
            return (Criteria) this;
        }

        public Criteria andPrivilegeNameEqualTo(String value) {
            addCriterion("privilege_name =", value, "privilegeName");
            return (Criteria) this;
        }

        public Criteria andPrivilegeNameNotEqualTo(String value) {
            addCriterion("privilege_name <>", value, "privilegeName");
            return (Criteria) this;
        }

        public Criteria andPrivilegeNameGreaterThan(String value) {
            addCriterion("privilege_name >", value, "privilegeName");
            return (Criteria) this;
        }

        public Criteria andPrivilegeNameGreaterThanOrEqualTo(String value) {
            addCriterion("privilege_name >=", value, "privilegeName");
            return (Criteria) this;
        }

        public Criteria andPrivilegeNameLessThan(String value) {
            addCriterion("privilege_name <", value, "privilegeName");
            return (Criteria) this;
        }

        public Criteria andPrivilegeNameLessThanOrEqualTo(String value) {
            addCriterion("privilege_name <=", value, "privilegeName");
            return (Criteria) this;
        }

        public Criteria andPrivilegeNameLike(String value) {
            addCriterion("privilege_name like", value, "privilegeName");
            return (Criteria) this;
        }

        public Criteria andPrivilegeNameNotLike(String value) {
            addCriterion("privilege_name not like", value, "privilegeName");
            return (Criteria) this;
        }

        public Criteria andPrivilegeNameIn(List<String> values) {
            addCriterion("privilege_name in", values, "privilegeName");
            return (Criteria) this;
        }

        public Criteria andPrivilegeNameNotIn(List<String> values) {
            addCriterion("privilege_name not in", values, "privilegeName");
            return (Criteria) this;
        }

        public Criteria andPrivilegeNameBetween(String value1, String value2) {
            addCriterion("privilege_name between", value1, value2, "privilegeName");
            return (Criteria) this;
        }

        public Criteria andPrivilegeNameNotBetween(String value1, String value2) {
            addCriterion("privilege_name not between", value1, value2, "privilegeName");
            return (Criteria) this;
        }

        public Criteria andPrivilegeUrlIsNull() {
            addCriterion("privilege_url is null");
            return (Criteria) this;
        }

        public Criteria andPrivilegeUrlIsNotNull() {
            addCriterion("privilege_url is not null");
            return (Criteria) this;
        }

        public Criteria andPrivilegeUrlEqualTo(String value) {
            addCriterion("privilege_url =", value, "privilegeUrl");
            return (Criteria) this;
        }

        public Criteria andPrivilegeUrlNotEqualTo(String value) {
            addCriterion("privilege_url <>", value, "privilegeUrl");
            return (Criteria) this;
        }

        public Criteria andPrivilegeUrlGreaterThan(String value) {
            addCriterion("privilege_url >", value, "privilegeUrl");
            return (Criteria) this;
        }

        public Criteria andPrivilegeUrlGreaterThanOrEqualTo(String value) {
            addCriterion("privilege_url >=", value, "privilegeUrl");
            return (Criteria) this;
        }

        public Criteria andPrivilegeUrlLessThan(String value) {
            addCriterion("privilege_url <", value, "privilegeUrl");
            return (Criteria) this;
        }

        public Criteria andPrivilegeUrlLessThanOrEqualTo(String value) {
            addCriterion("privilege_url <=", value, "privilegeUrl");
            return (Criteria) this;
        }

        public Criteria andPrivilegeUrlLike(String value) {
            addCriterion("privilege_url like", value, "privilegeUrl");
            return (Criteria) this;
        }

        public Criteria andPrivilegeUrlNotLike(String value) {
            addCriterion("privilege_url not like", value, "privilegeUrl");
            return (Criteria) this;
        }

        public Criteria andPrivilegeUrlIn(List<String> values) {
            addCriterion("privilege_url in", values, "privilegeUrl");
            return (Criteria) this;
        }

        public Criteria andPrivilegeUrlNotIn(List<String> values) {
            addCriterion("privilege_url not in", values, "privilegeUrl");
            return (Criteria) this;
        }

        public Criteria andPrivilegeUrlBetween(String value1, String value2) {
            addCriterion("privilege_url between", value1, value2, "privilegeUrl");
            return (Criteria) this;
        }

        public Criteria andPrivilegeUrlNotBetween(String value1, String value2) {
            addCriterion("privilege_url not between", value1, value2, "privilegeUrl");
            return (Criteria) this;
        }

        public Criteria andPrivilegeParentIdIsNull() {
            addCriterion("privilege_parent_id is null");
            return (Criteria) this;
        }

        public Criteria andPrivilegeParentIdIsNotNull() {
            addCriterion("privilege_parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andPrivilegeParentIdEqualTo(Integer value) {
            addCriterion("privilege_parent_id =", value, "privilegeParentId");
            return (Criteria) this;
        }

        public Criteria andPrivilegeParentIdNotEqualTo(Integer value) {
            addCriterion("privilege_parent_id <>", value, "privilegeParentId");
            return (Criteria) this;
        }

        public Criteria andPrivilegeParentIdGreaterThan(Integer value) {
            addCriterion("privilege_parent_id >", value, "privilegeParentId");
            return (Criteria) this;
        }

        public Criteria andPrivilegeParentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("privilege_parent_id >=", value, "privilegeParentId");
            return (Criteria) this;
        }

        public Criteria andPrivilegeParentIdLessThan(Integer value) {
            addCriterion("privilege_parent_id <", value, "privilegeParentId");
            return (Criteria) this;
        }

        public Criteria andPrivilegeParentIdLessThanOrEqualTo(Integer value) {
            addCriterion("privilege_parent_id <=", value, "privilegeParentId");
            return (Criteria) this;
        }

        public Criteria andPrivilegeParentIdIn(List<Integer> values) {
            addCriterion("privilege_parent_id in", values, "privilegeParentId");
            return (Criteria) this;
        }

        public Criteria andPrivilegeParentIdNotIn(List<Integer> values) {
            addCriterion("privilege_parent_id not in", values, "privilegeParentId");
            return (Criteria) this;
        }

        public Criteria andPrivilegeParentIdBetween(Integer value1, Integer value2) {
            addCriterion("privilege_parent_id between", value1, value2, "privilegeParentId");
            return (Criteria) this;
        }

        public Criteria andPrivilegeParentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("privilege_parent_id not between", value1, value2, "privilegeParentId");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}