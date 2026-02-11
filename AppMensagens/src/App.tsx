import { Route, Routes } from 'react-router-dom'
import './App.css'
import { Feed } from './pages/Feed/Feed'
import { Usuario } from './pages/Usuario/Usuario'
function App() {
  return (
    <Routes>
    <Route path='/' element={<Feed/>}/>
    <Route path='/:nomeUsuario' element={<Usuario/>}/>
    </Routes>
  )
}

export default App
