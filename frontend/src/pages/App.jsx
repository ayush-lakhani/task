import React, { useState } from 'react'
import axios from 'axios'

const api = axios.create({ baseURL: import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080' })

export default function App() {
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [fullName, setFullName] = useState('')
  const [role, setRole] = useState('STUDENT')
  const [token, setToken] = useState('')
  const [jobs, setJobs] = useState([])

  const authHeaders = token ? { Authorization: `Bearer ${token}` } : {}

  const register = async () => {
    const res = await api.post('/api/auth/register', { email, password, fullName, role })
    setToken(res.data.token)
  }
  const login = async () => {
    const res = await api.post('/api/auth/login', { email, password })
    setToken(res.data.token)
  }
  const loadJobs = async () => {
    const res = await api.get('/api/jobs')
    setJobs(res.data)
  }
  const createJob = async () => {
    await api.post('/api/jobs', {
      title: 'Sample Role',
      description: 'Test job',
      location: 'Remote',
      internship: true
    }, { headers: authHeaders })
    loadJobs()
  }

  return (
    <div style={{ padding: 24, fontFamily: 'sans-serif' }}>
      <h2>ZIDIO Connect</h2>
      <div style={{ display: 'grid', gap: 8, maxWidth: 360 }}>
        <input placeholder="Full name" value={fullName} onChange={e => setFullName(e.target.value)} />
        <input placeholder="Email" value={email} onChange={e => setEmail(e.target.value)} />
        <input placeholder="Password" type="password" value={password} onChange={e => setPassword(e.target.value)} />
        <select value={role} onChange={e => setRole(e.target.value)}>
          <option>STUDENT</option>
          <option>RECRUITER</option>
          <option>ADMIN</option>
        </select>
        <div style={{ display: 'flex', gap: 8 }}>
          <button onClick={register}>Register</button>
          <button onClick={login}>Login</button>
        </div>
        <div>Token: {token ? token.slice(0, 20) + '...' : '-'}</div>
      </div>

      <hr />
      <div style={{ display: 'flex', gap: 8 }}>
        <button onClick={loadJobs}>Load Jobs</button>
        <button onClick={createJob}>Create Sample Job (Recruiter/Admin)</button>
      </div>
      <ul>
        {jobs.map(j => (
          <li key={j.id}>{j.title} - {j.location}</li>
        ))}
      </ul>
    </div>
  )
}

