import React, { useEffect, useState } from "react";
import {SongListItem} from "./SongListItem";
import axios from "axios";

function SongList() {
  // const songs = [
  //   {
  //     id: 1,
  //     name: "Papercut",
  //     length: "3:04",
  //     artists: [{id: 1, name: "Linkin Park"}],
  //     album: {
  //       "id": 1,
  //       "name": "Hybrid Theory",
  //       "releaseYear": 2000
  //     }
  //   },
  // ];

  const [songs, setSongs] = useState([]);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/songs');
        setSongs(response.data);
        setIsLoading(false);
      } catch (error) {
        console.log(error);
      }
    };

    fetchData();
  }, []);

  return (
    <div>
      {songs.map(song => {
        return <SongListItem id={song.id} 
        name={song.name} 
        artists={song.artists}
        duration={song.duration}
        album={song.album} 
        key={song.id}/>
      })}
    </div>
  );
}

export default SongList;
