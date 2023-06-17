import { Route, Routes } from 'react-router'
import Home from './components/Home'
import {SongDetails} from './components/SongDetails'

function App() {
  return (
    <Routes>
      <Route path="/" exact element={<Home />} />
      <Route path="/song/:id" element={<SongDetails />} />
    </Routes>
  )
}

export default App
