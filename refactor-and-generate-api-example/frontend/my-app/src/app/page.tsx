"use client"
import { useState } from "react";

export default function Home() {
  const [input, setInput] = useState("");
  const [result, setResult] = useState("");

  const handleClick = (value: string) => {
    if (value === "=") {
      try {
        // Replace '×' and '÷' with '*' and '/' respectively for eval to work correctly
        let sanitizedInput = input.replace(/×/g, '*').replace(/÷/g, '/');
        // Handle percentage calculations
        // sanitizedInput = sanitizedInput.replace(/(\d+\.?\d*)%/g, '($1/100)');
        // Handle percentage operations
        sanitizedInput = sanitizedInput.replace(/(\d+\.?\d*)\s*([-+*/])\s*(\d+\.?\d*)%/g, '($1 $2 ($1 * $3 / 100))');
        // Evaluate the expression
        const evaluatedResult = eval(sanitizedInput);
        setResult(evaluatedResult.toString());
      } catch {
        setResult("Error");
      }
    } else if (value === "AC") {
      setInput("");
      setResult("");
    } else if (value === "±") {
      // Toggle the sign of the current input
      if (input) {
        if (input.startsWith("-")) {
          setInput(input.substring(1));
        } else {
          setInput("-" + input);
        }
      }
    } else {
      setInput(input + value);
    }
  };

  return (
    <div className="flex flex-col items-center justify-center min-h-screen p-8 bg-gray-800 text-white">
      <div className="w-80 bg-gray-900 rounded-lg shadow-lg p-4">
        <div className="text-right text-2xl mb-4">{input || "0"}</div>
        <div className="text-right text-4xl mb-4">{result}</div>
        <div className="grid grid-cols-4 gap-2">
          {"AC ± % ÷ 7 8 9 × 4 5 6 - 1 2 3 + 0 . =".split(" ").map((value) => (
            <button
              key={value}
              className={`p-4 rounded-lg ${value === "=" ? "col-span-2 bg-orange-500" : "bg-gray-700"} ${value === "0" ? "col-span-2" : ""}`}
              onClick={() => handleClick(value)}
            >
              {value}
            </button>
          ))}
        </div>
      </div>
    </div>
  );
}
