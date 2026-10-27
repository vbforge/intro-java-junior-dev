package com.vbforge.oop.investigation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RecordsAndEnumsDemoTest {

    @Test void recordEqualsIsValueBased() {
        assertThat(RecordsAndEnumsDemo.recordsHaveValueEquals()).isTrue();
    }

    @Test void recordHashCodeIsConsistent() {
        assertThat(RecordsAndEnumsDemo.recordsHaveConsistentHashCode()).isEqualTo(1);
    }

    @Test void recordToStringIsReadable() {
        assertThat(RecordsAndEnumsDemo.recordsHaveReadableToString())
                .isEqualTo("Point[x=3, y=4]");
    }

    @Test void recordCompactConstructorValidates() {
        assertThat(RecordsAndEnumsDemo.recordValidation()).contains("from").contains("to");
    }

    @Test void enumConstantsAreSingletons() {
        assertThat(RecordsAndEnumsDemo.enumIsSingleton()).isTrue();
    }

    @Test void enumSwitchExpression() {
        assertThat(RecordsAndEnumsDemo.enumSwitchExpression(RecordsAndEnumsDemo.Day.MON))
                .isEqualTo("weekday");
        assertThat(RecordsAndEnumsDemo.enumSwitchExpression(RecordsAndEnumsDemo.Day.SUN))
                .isEqualTo("weekend");
    }

    @Test void enumValuesPreserveOrder() {
        assertThat(RecordsAndEnumsDemo.valuesOrder()).isEqualTo(7);
    }

    @Test void enumValueOfWorks() {
        assertThat(RecordsAndEnumsDemo.valueOfWorks()).isEqualTo(RecordsAndEnumsDemo.Planet.EARTH);
    }
}