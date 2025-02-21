"use client"

import { useEffect, useState } from 'react'

export default function UsersPage() {
  const [users, setUsers] = useState<any[]>([])
  const [isLoading, setIsLoading] = useState(false)
  const [error, setError] = useState('')
  const [newUser, setNewUser] = useState({ name: '', email: '', address: '', phoneNumber: '', age: '' })
  const [isSubmitting, setIsSubmitting] = useState(false)
  const [submitError, setSubmitError] = useState('')

  useEffect(() => {
    setIsLoading(true)
    setError('')
    fetch('http://localhost:8080/users')
      .then(res => {
        if (!res.ok) throw new Error('Failed to fetch')
        return res.json()
      })
      .then(data => {
        setUsers(data)
        setIsLoading(false)
      })
      .catch(err => {
        setError('Error loading users: ' + err.message)
        setIsLoading(false)
      })
  }, [])

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault()
    setIsSubmitting(true)
    setSubmitError('')

    // Bad: Direct mutation of state
    const userToSubmit = {...newUser, age: parseInt(newUser.age)}

    fetch('http://localhost:8080/users', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(userToSubmit),
    })
      .then(res => {
        if (!res.ok) throw new Error('Failed to create user')
        return res.json()
      })
      .then(data => {
        // Bad: Direct state mutation
        setUsers([...users, data])
        setIsSubmitting(false)
        // Bad: Multiple setState calls
        setNewUser({ name: '', email: '', address: '', phoneNumber: '', age: '' })
      })
      .catch(err => {
        setSubmitError('Error creating user: ' + err.message)
        setIsSubmitting(false)
      })
  }

  return (
    <div style={{ padding: '20px', maxWidth: '800px', margin: '0 auto' }}>
      <h1 style={{ color: '#333', marginBottom: '20px' }}>Users</h1>
      
      {error && <div style={{ color: 'red', marginBottom: '10px' }}>{error}</div>}
      {isLoading && <div>Loading...</div>}
      
      <form onSubmit={handleSubmit} style={{ marginBottom: '20px' }}>
        <input
          type="text"
          value={newUser.name}
          onChange={(e) => setNewUser({...newUser, name: e.target.value})}
          placeholder="Name"
          style={{ marginRight: '10px' }}
        />
        <input
          type="email"
          value={newUser.email}
          onChange={(e) => setNewUser({...newUser, email: e.target.value})}
          placeholder="Email"
          style={{ marginRight: '10px' }}
        />
        <input
          type="text"
          value={newUser.address}
          onChange={(e) => setNewUser({...newUser, address: e.target.value})}
          placeholder="Address"
          style={{ marginRight: '10px' }}
        />
        <input
          type="tel"
          value={newUser.phoneNumber}
          onChange={(e) => setNewUser({...newUser, phoneNumber: e.target.value})}
          placeholder="Phone Number"
          style={{ marginRight: '10px' }}
        />
        <input
          type="number"
          value={newUser.age}
          onChange={(e) => setNewUser({...newUser, age: e.target.value})}
          placeholder="Age"
          style={{ marginRight: '10px' }}
        />
        <button 
          type="submit" 
          disabled={isSubmitting}
          style={{ 
            backgroundColor: '#007bff', 
            color: 'white', 
            padding: '5px 10px', 
            border: 'none' 
          }}
        >
          Add User
        </button>
      </form>

      {submitError && <div style={{ color: 'red', marginBottom: '10px' }}>{submitError}</div>}

      <ul style={{ listStyle: 'none', padding: 0 }}>
        {users.map((user: any) => (
          <li key={user.id} style={{ 
            padding: '10px', 
            border: '1px solid #ddd', 
            marginBottom: '5px' 
          }}>
            <div>Name: {user.name}</div>
            <div>Email: {user.email}</div>
            <div>Address: {user.address}</div>
            <div>Phone: {user.phoneNumber}</div>
            <div>Age: {user.age}</div>
          </li>
        ))}
      </ul>
    </div>
  )
}