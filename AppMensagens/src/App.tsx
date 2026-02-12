import { Route, Routes } from 'react-router-dom'
import './App.css'
import { Feed } from './pages/Feed/Feed'
import { Usuario } from './pages/Usuario/Usuario'
import { Cadastro } from './pages/Login/Cadastro'
import { Login } from './pages/Login/Login'


function App() {
  return (
    <Routes>
    <Route path='/auth/register' element={<Cadastro/>}/>
    <Route path='/auth/login' element={<Login/>}/>
    <Route path='/' element={<Feed/>}/>
    <Route path='/:nomeUsuario' element={<Usuario/>}/>
    </Routes>
  )
}

export default App
