Add spec testgen coverage verification

This PR documents the verification that all 14 test cases generated from the specification-based test generation are covered by existing tests.

**Spec used:** "null returns null; empty means 0; digits 0..9 only; carry; unequal lengths; no leading zeros unless [0]"

**Generated test cases verified:**
- ✅ left_null, right_null, both_empty, left_empty, right_empty (ECP)
- ✅ digit_negative, digit_above_9, digit_at_lower_bound, digit_at_upper_bound (BVA)
- ✅ single_carry, carry_chain, unequal_lengths_no_carry, unequal_lengths_with_carry (ECP/BVA)
- ✅ leading_zeros_trim (BVA)

**Coverage achieved:** 100% branch coverage / 100% instruction coverage confirmed via JaCoCo.

All tests pass: 22/22 ✅