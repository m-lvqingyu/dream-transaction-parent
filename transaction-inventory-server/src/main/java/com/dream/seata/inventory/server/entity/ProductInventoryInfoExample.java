package com.dream.seata.inventory.server.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductInventoryInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public ProductInventoryInfoExample() {
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

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getOffset() {
        return offset;
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

        public Criteria andInventoryIdIsNull() {
            addCriterion("inventory_id is null");
            return (Criteria) this;
        }

        public Criteria andInventoryIdIsNotNull() {
            addCriterion("inventory_id is not null");
            return (Criteria) this;
        }

        public Criteria andInventoryIdEqualTo(Long value) {
            addCriterion("inventory_id =", value, "inventoryId");
            return (Criteria) this;
        }

        public Criteria andInventoryIdNotEqualTo(Long value) {
            addCriterion("inventory_id <>", value, "inventoryId");
            return (Criteria) this;
        }

        public Criteria andInventoryIdGreaterThan(Long value) {
            addCriterion("inventory_id >", value, "inventoryId");
            return (Criteria) this;
        }

        public Criteria andInventoryIdGreaterThanOrEqualTo(Long value) {
            addCriterion("inventory_id >=", value, "inventoryId");
            return (Criteria) this;
        }

        public Criteria andInventoryIdLessThan(Long value) {
            addCriterion("inventory_id <", value, "inventoryId");
            return (Criteria) this;
        }

        public Criteria andInventoryIdLessThanOrEqualTo(Long value) {
            addCriterion("inventory_id <=", value, "inventoryId");
            return (Criteria) this;
        }

        public Criteria andInventoryIdIn(List<Long> values) {
            addCriterion("inventory_id in", values, "inventoryId");
            return (Criteria) this;
        }

        public Criteria andInventoryIdNotIn(List<Long> values) {
            addCriterion("inventory_id not in", values, "inventoryId");
            return (Criteria) this;
        }

        public Criteria andInventoryIdBetween(Long value1, Long value2) {
            addCriterion("inventory_id between", value1, value2, "inventoryId");
            return (Criteria) this;
        }

        public Criteria andInventoryIdNotBetween(Long value1, Long value2) {
            addCriterion("inventory_id not between", value1, value2, "inventoryId");
            return (Criteria) this;
        }

        public Criteria andProductUidIsNull() {
            addCriterion("product_uid is null");
            return (Criteria) this;
        }

        public Criteria andProductUidIsNotNull() {
            addCriterion("product_uid is not null");
            return (Criteria) this;
        }

        public Criteria andProductUidEqualTo(String value) {
            addCriterion("product_uid =", value, "productUid");
            return (Criteria) this;
        }

        public Criteria andProductUidNotEqualTo(String value) {
            addCriterion("product_uid <>", value, "productUid");
            return (Criteria) this;
        }

        public Criteria andProductUidGreaterThan(String value) {
            addCriterion("product_uid >", value, "productUid");
            return (Criteria) this;
        }

        public Criteria andProductUidGreaterThanOrEqualTo(String value) {
            addCriterion("product_uid >=", value, "productUid");
            return (Criteria) this;
        }

        public Criteria andProductUidLessThan(String value) {
            addCriterion("product_uid <", value, "productUid");
            return (Criteria) this;
        }

        public Criteria andProductUidLessThanOrEqualTo(String value) {
            addCriterion("product_uid <=", value, "productUid");
            return (Criteria) this;
        }

        public Criteria andProductUidLike(String value) {
            addCriterion("product_uid like", value, "productUid");
            return (Criteria) this;
        }

        public Criteria andProductUidNotLike(String value) {
            addCriterion("product_uid not like", value, "productUid");
            return (Criteria) this;
        }

        public Criteria andProductUidIn(List<String> values) {
            addCriterion("product_uid in", values, "productUid");
            return (Criteria) this;
        }

        public Criteria andProductUidNotIn(List<String> values) {
            addCriterion("product_uid not in", values, "productUid");
            return (Criteria) this;
        }

        public Criteria andProductUidBetween(String value1, String value2) {
            addCriterion("product_uid between", value1, value2, "productUid");
            return (Criteria) this;
        }

        public Criteria andProductUidNotBetween(String value1, String value2) {
            addCriterion("product_uid not between", value1, value2, "productUid");
            return (Criteria) this;
        }

        public Criteria andCurrentInventoryNumIsNull() {
            addCriterion("current_inventory_num is null");
            return (Criteria) this;
        }

        public Criteria andCurrentInventoryNumIsNotNull() {
            addCriterion("current_inventory_num is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentInventoryNumEqualTo(Long value) {
            addCriterion("current_inventory_num =", value, "currentInventoryNum");
            return (Criteria) this;
        }

        public Criteria andCurrentInventoryNumNotEqualTo(Long value) {
            addCriterion("current_inventory_num <>", value, "currentInventoryNum");
            return (Criteria) this;
        }

        public Criteria andCurrentInventoryNumGreaterThan(Long value) {
            addCriterion("current_inventory_num >", value, "currentInventoryNum");
            return (Criteria) this;
        }

        public Criteria andCurrentInventoryNumGreaterThanOrEqualTo(Long value) {
            addCriterion("current_inventory_num >=", value, "currentInventoryNum");
            return (Criteria) this;
        }

        public Criteria andCurrentInventoryNumLessThan(Long value) {
            addCriterion("current_inventory_num <", value, "currentInventoryNum");
            return (Criteria) this;
        }

        public Criteria andCurrentInventoryNumLessThanOrEqualTo(Long value) {
            addCriterion("current_inventory_num <=", value, "currentInventoryNum");
            return (Criteria) this;
        }

        public Criteria andCurrentInventoryNumIn(List<Long> values) {
            addCriterion("current_inventory_num in", values, "currentInventoryNum");
            return (Criteria) this;
        }

        public Criteria andCurrentInventoryNumNotIn(List<Long> values) {
            addCriterion("current_inventory_num not in", values, "currentInventoryNum");
            return (Criteria) this;
        }

        public Criteria andCurrentInventoryNumBetween(Long value1, Long value2) {
            addCriterion("current_inventory_num between", value1, value2, "currentInventoryNum");
            return (Criteria) this;
        }

        public Criteria andCurrentInventoryNumNotBetween(Long value1, Long value2) {
            addCriterion("current_inventory_num not between", value1, value2, "currentInventoryNum");
            return (Criteria) this;
        }

        public Criteria andLockNumIsNull() {
            addCriterion("lock_num is null");
            return (Criteria) this;
        }

        public Criteria andLockNumIsNotNull() {
            addCriterion("lock_num is not null");
            return (Criteria) this;
        }

        public Criteria andLockNumEqualTo(Long value) {
            addCriterion("lock_num =", value, "lockNum");
            return (Criteria) this;
        }

        public Criteria andLockNumNotEqualTo(Long value) {
            addCriterion("lock_num <>", value, "lockNum");
            return (Criteria) this;
        }

        public Criteria andLockNumGreaterThan(Long value) {
            addCriterion("lock_num >", value, "lockNum");
            return (Criteria) this;
        }

        public Criteria andLockNumGreaterThanOrEqualTo(Long value) {
            addCriterion("lock_num >=", value, "lockNum");
            return (Criteria) this;
        }

        public Criteria andLockNumLessThan(Long value) {
            addCriterion("lock_num <", value, "lockNum");
            return (Criteria) this;
        }

        public Criteria andLockNumLessThanOrEqualTo(Long value) {
            addCriterion("lock_num <=", value, "lockNum");
            return (Criteria) this;
        }

        public Criteria andLockNumIn(List<Long> values) {
            addCriterion("lock_num in", values, "lockNum");
            return (Criteria) this;
        }

        public Criteria andLockNumNotIn(List<Long> values) {
            addCriterion("lock_num not in", values, "lockNum");
            return (Criteria) this;
        }

        public Criteria andLockNumBetween(Long value1, Long value2) {
            addCriterion("lock_num between", value1, value2, "lockNum");
            return (Criteria) this;
        }

        public Criteria andLockNumNotBetween(Long value1, Long value2) {
            addCriterion("lock_num not between", value1, value2, "lockNum");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("version is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("version is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(Long value) {
            addCriterion("version =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(Long value) {
            addCriterion("version <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(Long value) {
            addCriterion("version >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(Long value) {
            addCriterion("version >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(Long value) {
            addCriterion("version <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(Long value) {
            addCriterion("version <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<Long> values) {
            addCriterion("version in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<Long> values) {
            addCriterion("version not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(Long value1, Long value2) {
            addCriterion("version between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(Long value1, Long value2) {
            addCriterion("version not between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }
    }

    /**
     */
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