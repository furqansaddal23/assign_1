package org.example;

import org.junit.jupiter.api.Test;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.*;

class NumberUtilsTest {

    /*
  -- Partitions
  Single input (left, right):
 null
 empty
 size == 1 and size > 1
 valid digits and invalid digits
 leading zeros (size>1 && first==0)
interaction:
 any null (left/right/both)
 any empty (left/right/both)
 same length vs different length
 no carry over vs carry over vs carry-chain vs final carry (new digit)
Output:
- null
- length = maxLen
- length = maxLen + 1

  -- Spec Testgen Coverage (March 6, 2026)
  All 14 generated test cases from spec "null returns null; empty means 0; digits 0..9 only; carry; unequal lengths; no leading zeros unless [0]" are covered:
  ✓ left_null, right_null, both_empty, left_empty, right_empty
  ✓ digit_negative, digit_above_9, digit_at_lower_bound, digit_at_upper_bound
  ✓ single_carry, carry_chain, unequal_lengths_no_carry, unequal_lengths_with_carry
  ✓ leading_zeros_trim
    */
   
    @Test
    void addreturnsNullwhenLeftIsNull() {
        // left = null, right = [1,2] -> null
        assertThat(NumberUtils.add(null, asList(1, 2))).isNull();
    }

    @Test 
    void constructor_canBeCalled(){
        assertThat(new NumberUtils()).isNotNull();
    }

    @Test
    void addreturnsNullwhenRightIsNull() {
        // left = [1,2], right = null -> null
        assertThat(NumberUtils.add(asList(1, 2), null)).isNull();
    }
    @Test
    void addbothNullreturnsNull() {
        // left = null, right = null -> null
        assertThat(NumberUtils.add(null, null)).isNull();
    }


    @Test
    void addsingleDigitnoCarryOverreturnsSingleDigitSum() {
        // left = [2], right [3] -> [5]
        assertThat(NumberUtils.add(asList(2), asList(3)))
                .containsExactly(5);
    }

    @Test
    void adddifferentLengthsnoCarryOverreturnsCorrectSum() {
        // left = [1,4,2], right = [3,5] -> [1,6,8]
        assertThat(NumberUtils.add(asList(1, 4, 2), asList(3, 5)))
                .containsExactly(1, 7, 7);
    }

    @Test
    void addinternalCarrynoNewLeadingDigitreturnsCorrectSum() {
        // left = [1,7], right = [2,1] -> [3,8]
        assertThat(NumberUtils.add(asList(1, 7), asList(2, 1)))
                .containsExactly(3, 8);
    }

    @Test
    void addcarryCreatesNewLeadingDigitreturnsCorrectSum() {
        // left = [9,7] , right = [3] -> [1, 0, 0]
        assertThat(NumberUtils.add(asList(9, 7), asList(3)))
                .containsExactly(1, 0, 0);
    }

    @Test
    void addthrowsIllegalArgumentExceptionwhenDigitNegative() {
        // left = [3,1,-2] , right [4,2] -> invalid since there is a negative value, returning IllegalArgument Exception
        assertThatThrownBy(() -> NumberUtils.add(asList(3, 1, -2), asList(4, 2)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void addthrowsIllegalArgumentExceptionwhenDigitAbove9() {
        // left = [1,11,2] , right [4,2] -> invalid since there is a  value greater than 9, returning IllegalArgument Exception
        assertThatThrownBy(() -> NumberUtils.add(asList(1, 11, 2), asList(4, 2)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    // tests created by the chat bot

    @Test
    void addremovesLeadingZerosInResult() {
        // left = [0,0,1], right = [0] -> [1]
        assertThat(NumberUtils.add(asList(0, 0, 1), asList(0)))
                .containsExactly(1);
    }

    @Test
    void addEmptyLeftReturnsRight() {
        // left = [], right = [4,2] -> [4,2]
        assertThat(NumberUtils.add(asList(), asList(4, 2)))
                .containsExactly(4, 2);
    }

    @Test
    void addEmptyRightReturnsLeft() {
        // left = [1,2], right = [] -> [1,2]
        assertThat(NumberUtils.add(asList(1, 2), asList()))
                .containsExactly(1, 2);
    }

    @Test
    void addBothEmptyReturnsEmpty() {
        // left = [], right = [] -> []
        assertThat(NumberUtils.add(asList(), asList()))
                .isEmpty();
    }

    

    @Test
    void addRightDigitNegativeThrowsException() {
        // left = [3,2], right = [4,-1] -> invalid
        assertThatThrownBy(() -> NumberUtils.add(asList(3, 2), asList(4, -1)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void addRightDigitAbove9ThrowsException() {
        // left = [3,2], right = [4,15] -> invalid
        assertThatThrownBy(() -> NumberUtils.add(asList(3, 2), asList(4, 15)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void addZeroAndZeroReturnsZero() {
        // left = [0], right = [0] -> [0]
        assertThat(NumberUtils.add(asList(0), asList(0)))
                .containsExactly(0);
    }

    @Test
    void addMultipleCarryChain() {
        // left = [9,9,9], right = [1] -> [1,0,0,0]
        assertThat(NumberUtils.add(asList(9, 9, 9), asList(1)))
                .containsExactly(1, 0, 0, 0);
    }

    @Test
    void addSameLengthWithCarry() {
        // left = [9,5], right = [5,5] -> [1,5,0]
        assertThat(NumberUtils.add(asList(9, 5), asList(5, 5)))
                .containsExactly(1, 5, 0);
    }

    @Test
    void addMultipleLeadingZeros() {
        // left = [0,0,0,5], right = [0,0,0,3] -> [8]
        assertThat(NumberUtils.add(asList(0, 0, 0, 5), asList(0, 0, 0, 3)))
                .containsExactly(8);
    }

    @Test
    void addLargeNumbersNoCarry() {
        // left = [5,4,3,2,1], right = [1,2,3,4] -> [5,5,5,5,5]
        assertThat(NumberUtils.add(asList(5, 4, 3, 2, 1), asList(1, 2, 3, 4)))
                .containsExactly(5, 5, 5, 5, 5);
    }

    @Test
    void addBoundaryDigit9And9() {
        // left = [9], right = [9] -> [1,8]
        assertThat(NumberUtils.add(asList(9), asList(9)))
                .containsExactly(1, 8);
    }

} 