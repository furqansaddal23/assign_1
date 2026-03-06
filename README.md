# SE333 Final Project — Coverage-Driven Testing Agent (MCP + JaCoCo)

## Project Summary
This project demonstrates a prompt-driven testing workflow in VS Code that improves a Java Maven project's test suite using JaCoCo coverage feedback.

## What I Built
- **MCP server (FastMCP + SSE):** Connected to VS Code and validated tool invocation.
- **Tester prompt agent:** A `.github/prompts/tester.prompt.md` prompt that guides an iterative testing loop:
  1) generate/improve tests  
  2) run `mvn test`  
  3) read `target/site/jacoco/jacoco.xml`  
  4) identify uncovered branches/methods  
  5) add targeted tests  
  6) repeat

## Key Results
- Started with **partial coverage**
- Agent identified uncovered branches/methods via **JaCoCo XML**
- Agent added tests for:
  - null inputs
  - empty inputs
  - carry logic
  - invalid digits (negative and > 9)
  - leading zeros
  - unequal length inputs
- Final result reached **100% instruction coverage / 100% branch coverage**

## How to Run
### Run tests + generate coverage report
```bash
mvn clean test
```

## Phase 4 – GitHub MCP Automation
This phase demonstrates automated GitHub operations using MCP tools:
- Branch creation and management
- Automated testing and coverage verification
- Commit and push operations
- Pull request creation and management
- Integration with trunk-based development workflow