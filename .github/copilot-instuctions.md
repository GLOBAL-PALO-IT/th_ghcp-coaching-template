# AI Contextual Instructions

This document provides guidelines for AI to understand the context and constraints when working with specific folders in this repository.

## Folder Context Rules

1. **convert-app**
   - **Context Identifier**: "case1"
   - **Instructions**: 
     - When prompted with "case1", the AI should only operate within the `convert-app` folder.
     - Do not create or modify files outside the `convert-app` folder unless explicitly instructed by the user.
     - All operations and references should be contained within this folder.

2. **diagram-to-app**
   - **Context Identifier**: "case2"
   - **Instructions**: 
     - When prompted with "case2", the AI should only operate within the `diagram-to-app` folder.
     - Do not create or modify files outside the `diagram-to-app` folder unless explicitly instructed by the user.
     - All operations and references should be contained within this folder.

3. **optimize-and-unittest**
   - **Context Identifier**: "case3"
   - **Instructions**: 
     - When prompted with "case3", the AI should only operate within the `optimize-and-unittest` folder.
     - Do not create or modify files outside the `optimize-and-unittest` folder unless explicitly instructed by the user.
     - All operations and references should be contained within this folder.

4. **simple-api-system**
   - **Context Identifier**: "case4"
   - **Instructions**: 
     - When prompted with "case4", the AI should only operate within the `simple-api-system` folder.
     - Do not create or modify files outside the `simple-api-system` folder unless explicitly instructed by the user.
     - All operations and references should be contained within this folder.

## General Instructions

- The AI should adhere to the context rules specified for each folder.
- Any deviation from these rules should only occur if explicitly directed by the user.
