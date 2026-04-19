import { useState } from 'react'
import { BrowserRouter, Route, Routes, useParams } from 'react-router'
import Details from './pages/Details'
import './styles/App.css'
import FetchEmployees from './components/FetchEmployees'
import EditEmployees from './components/EditEmployees'
import LoginPage from './pages/LoginPage'


function App() {
  const [count, setCount] = useState(0)
  const {id} = useParams();

  return (
    <>
    <BrowserRouter>
      <Routes>
        <Route path="/" element ={<Details/>}/>
        <Route path="/allEmployees" element ={<FetchEmployees/>}/>
        <Route path="/employees/:id" element ={<EditEmployees/>}/>
        <Route path="/login" element ={<LoginPage/>}/>
      </Routes>
    </BrowserRouter>
    </>
  )
}

export default App
