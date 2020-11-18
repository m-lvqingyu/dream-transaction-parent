package com.dream.seata.core.utils;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author Lv.QIngYu
 */
public class BigDecimalUtils {

    public static BigDecimal objectConvertBigDecimal(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof BigDecimal) {
            return (BigDecimal) value;
        } else if (value instanceof String) {
            return new BigDecimal((String) value);
        } else if (value instanceof BigInteger) {
            return new BigDecimal((BigInteger) value);
        } else if (value instanceof Number) {
            return new BigDecimal(((Number) value).doubleValue());
        } else {
            throw new ClassCastException("Not possible to coerce [" + value + "] from class " + value.getClass() + " into a BigDecimal.");
        }
    }
}
