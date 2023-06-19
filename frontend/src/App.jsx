import { Route, Routes } from 'react-router'
import Home from './components/Home'
import {SongDetails} from './components/SongDetails'
import { AddSong } from './components/AddSong'
import { AddAlbum } from './components/AddAlbum'
import { AddArtist } from './components/AddArtist'
import { Artists } from './components/Artists'
import Albums from './components/Albums'
import Metrics from './components/Metrics'

function App() {
  return (
    <Routes>
      <Route path="/" exact element={<Home />} />
      <Route path="/albums" exact element={<Albums />} />
      <Route path="/artists" exact element={<Artists />} />
      <Route path="/metrics" exact element={<Metrics />} />
      <Route path="/song/new" exact element={<AddSong />} />
      <Route path="/album/new" exact element={<AddAlbum />} />
      <Route path="/artist/new" exact element={<AddArtist />} />
      <Route path="/song/:id" element={<SongDetails />} />
    </Routes>
  )
}

export default App
