import { useState } from 'react'

export default function App() {
  const [count, setCount] = useState(0)

  return (
    <main style={{ fontFamily: 'sans-serif', padding: '2rem' }}>
      <h1>React Interview Prep Demo</h1>
      <p>Click the button to verify the dev server is working.</p>
      <button onClick={() => setCount((value) => value + 1)}>
        Count: {count}
      </button>
    </main>
  )
}
