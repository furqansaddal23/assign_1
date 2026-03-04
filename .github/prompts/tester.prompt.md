---
mode: tester-agent
description: "Generate, run, and improve tests using JaCoCo coverage feedback."
model: GPT-5.2
tools:
  - read
  - edit
  - execute
  - search
---

## Follow instructions below: ##
1. Write or improve JUnit tests for this Maven project.
2. Run `mvn test`.
3. If tests fail, debug and fix the issue.
4. Find `target/site/jacoco/jacoco.xml`.
5. Identify uncovered methods, lines, or branches.
6. Add tests targeting those gaps.
7. Re-run tests and compare coverage.
8. Repeat until coverage improves.