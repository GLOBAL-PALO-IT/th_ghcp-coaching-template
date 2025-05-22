# Svelte to Next.js Conversion with GitHub Copilot 🚀

## Overview

This session will guide you through the practical process of converting a simple **Svelte** application into a **Next.js** application. Our key accelerator in this transformation will be **GitHub Copilot**, demonstrating its power in code migration. ✨

The **source Svelte application** is intentionally concise, featuring two core pages:

* **Dashboard:** Displays mock data for "Top Selling Products" and "Top Spending." 📈💰
* **Profile:** Contains generic user information. 👤

Our objective is to perform a **systematic, page-by-page migration**, leveraging GitHub Copilot's contextual understanding to rewrite Svelte components into their Next.js equivalents. This will illustrate a pragmatic approach to framework transitions. 🏗️🔄

---

## Technical Session Guide for the Instructor

Please follow these steps to lead an effective and engaging session on this conversion process:

### Step 1: Initial Project Setup & Environment Verification 🛠️✅

1.  **Project Structure Introduction:** Begin by explaining the project's layout. Open the main project folder in your chosen code editor (e.g., VS Code). Highlight the two distinct sub-folders:
    * `source/`: The original Svelte application, which serves as our migration source.
    * `target/`: The new Next.js application, our destination framework.
2.  **Dependency Installation & Sanity Checks:**
    * Instruct participants to navigate into both the `source/` and `target/` directories in their terminal.
    * Execute `npm install` (or `yarn install`) in each directory. Emphasize the importance of a clean `node_modules` for both projects. 📦
    * **Verify Svelte App Functionality:** Run `npm run dev` (or `yarn dev`) in the `source/` directory. Open the application in the browser (`localhost:5000` or similar). Confirm both the Dashboard and Profile pages load correctly and without console errors. This establishes our baseline. 👍
    * **Verify Next.js App Initial State:** In the `target/` directory, run `npm run dev` (or `yarn dev`). Open this application (`localhost:3000`). Point out that it will likely display the default Next.js welcome page or an empty layout. This is crucial for understanding the "before" state of our target project. 😉

---

### Step 2: Page-by-Page Conversion - The Dashboard 📊➡️⚛️

This step focuses on a deep dive into converting the Dashboard page.

1.  **Contextualizing Copilot - Opening the Source Svelte Page:**
    * In the `source/` project, open the Svelte component file for the Dashboard (e.g., `src/routes/page.svelte`).
    * **Crucial Point:** Explain to participants that having the Svelte source open provides GitHub Copilot with the necessary context and syntax to understand the original page's structure, logic, and data flow. This is key to accurate conversion. 🧠
2.  **Prompting GitHub Copilot for Framework Transformation:**
    * Demonstrate activating GitHub Copilot (via the chat interface).
    * Issue the precise prompt:
        ```
        @workspace Rewrite this Svelte page as a Next.js page.
        ```
    * **Discussion Point:** Discuss how `@workspace` helps Copilot understand the broader project context, not just the single file. Observe the generated Next.js code. ✨
3.  **Code Validation & Refinement:**
    * **Instructor-led Code Review:** Guide participants through a critical review of the generated Next.js code. Focus on:
        * **Next.js Page Structure:** Does it use `export default function MyPage()`? Is it placed correctly in the directory?
        * **Reactivity/State Management:** How are Svelte's reactive declarations (`$:`) or stores (if present) translated to React's `useState` or `useEffect`?
        * **Component Composition:** Are any nested components handled appropriately?
        * **Styling:** How are Svelte's `<style>` blocks or CSS classes translated? (Note: Copilot might generate inline styles or suggest CSS Modules).
        * **Imports:** Are all necessary React/Next.js imports present (e.g., `import React from 'react'`, `import Head from 'next/head'`)? 📚⚙️
    * **Manual Adjustments (if needed):** Emphasize that while Copilot is powerful, human oversight and minor manual tweaks are often required for optimal results (e.g., specific data fetching methods like `use server` or `use client` if the page required data from an API).
    * Once satisfied, copy the generated Next.js code. 📝
4.  **Integrating into the Next.js Project:**
    * In the `target/` Next.js project, create a new file for the Dashboard page. For standard routing, this would be `dashboard/page.tsx`
    * Paste the validated Next.js code into this new file. 📋
5.  **Runtime Verification & Debugging:**
    * **Save** the new file in the Next.js project.
    * Navigate back to the browser where the Next.js app is running and access the `/` route.
    * **Crucial Step:** Confirm the converted Dashboard page renders correctly in the Next.js application. If errors occur, use this as an opportunity to demonstrate debugging techniques specific to Next.js (e.g., checking browser console, terminal output). 🐛🥳

---

### Step 3: Page-by-Page Conversion - The Profile Page 👤➡️⚛️

Reinforce the process by converting the second page.

1.  **Replication of Conversion Steps:** Instruct participants to follow the exact same methodology used for the Dashboard page, but for the Profile page:
    * Open the Svelte Profile component file in the `source/` project (e.g., `src/routes/profile/page.svelte`).
    * Utilize the identical GitHub Copilot prompt: `@workspace Rewrite this Svelte page as a Next.js page.`
    * **Independent Validation:** Encourage participants to independently validate and copy the generated Next.js code for the Profile page. ✅
    * Create a new file in the `target/` Next.js project for the Profile page (e.g., `pages/profile.js`).
    * Paste the code into this new file. 📋
    * **Final Verification:** Verify the converted Profile page in the Next.js application by navigating to the `/profile` route. 🎉

---