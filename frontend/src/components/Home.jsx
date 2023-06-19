import React, { useEffect, useState } from "react";
import Navbar from "./Navbar";
import { NavLink } from "react-router-dom";
import axios from "axios";
import { SongListItem } from "./SongListItem";

function Home() {
  const [songs, setSongs] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [searchPhrase, setSearchPhrase] = useState("");

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get("http://localhost:8080/api/songs");
        setSongs(response.data);
        setIsLoading(false);
      } catch (error) {
        console.log(error);
      }
    };

    fetchData();
  }, []);

  const handleSearch = async () => {
    try {
      if (searchPhrase) {
        const response = await axios.get(
          `http://localhost:8080/api/songs?name=${searchPhrase}`
        );
        setSongs(response.data);
      } else {
        const response = await axios.get("http://localhost:8080/api/songs");
        setSongs(response.data);
      }
    } catch (error) {
      console.log(error);
    }
  };

  const handleEnter = (event) => {
    if (event.key === 'Enter') {
      handleSearch()
    }
  }

  const handleSearchPhrase = (e) => {
    setSearchPhrase(e.target.value);
  };

  return (
    <div className="h-screen w-full bg-base-100">
      <Navbar />
      <div className="flex flex-row items-center justify-between p-10 -mb-10">
        <div className="flex gap-2">
          <input
            type="text"
            placeholder="Search song"
            className="input input-bordered input-md w-full max-w-xs"
            onChange={handleSearchPhrase}
            onKeyDown={handleEnter}
          />
          <button className="btn btn-primary" onClick={handleSearch}>
            Search
          </button>
        </div>
        <h1 className="text-3xl">All songs: </h1>
        <div className="flex flex-row gap-4 justify-end">
          <NavLink to={"/song/new"}>
            <button className="btn btn-outline btn-primary">Add song</button>
          </NavLink>
          <NavLink to={"/album/new"}>
            <button className="btn btn-outline btn-primary">Add album</button>
          </NavLink>
          <NavLink to={"/artist/new"}>
            <button className="btn btn-outline btn-primary">Add artist</button>
          </NavLink>
        </div>
      </div>
      <div className="p-10">
        <div>
          {songs.map((song) => {
            return (
              <SongListItem
                id={song.id}
                name={song.name}
                artists={song.artists}
                duration={song.duration}
                album={song.album}
                key={song.id}
              />
            );
          })}
        </div>
      </div>
    </div>
  );
}

export default Home;
