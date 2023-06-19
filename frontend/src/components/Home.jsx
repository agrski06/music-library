import React, { useEffect, useState } from "react";
import Navbar from "./Navbar";
import { NavLink } from "react-router-dom";
import axios from "axios";
import { SongListItem } from "./SongListItem";

function Home() {
  const [songs, setSongs] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [searchPhrase, setSearchPhrase] = useState("");
  const [params, setParams] = useState({});

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get("http://localhost:8080/api/songs", { params: params });
        setSongs(response.data);
        setIsLoading(false);
      } catch (error) {
        console.log(error);
      }
    };

    fetchData();
  }, [params]);

  const handleSearch = async () => {
    try {
      if (searchPhrase) {
        setParams((prev) => ({
          ...prev,
          name: searchPhrase,
        }));
      } else {
        // remove name from params
        let state = { ...params };
        delete state.name;
        setParams(state);

      }
    } catch (error) {
      console.log(error);
    }
  };

  const handleEnter = (event) => {
    if (event.key === "Enter") {
      handleSearch();
    }
  };

  const handleSearchPhrase = (e) => {
    setSearchPhrase(e.target.value);
  };

  const handleSortChange = async (e) => {
    if (e.target.value === "album") {
      setParams((prev) => ({
        ...prev,
        sort: "ALBUM",
      }));
    } else if (e.target.value === "song") {
      setParams((prev) => ({
        ...prev,
        sort: "SONG",
      }));
    }
  };

  return (
    <div className="h-screen w-full bg-base-100">
      <Navbar />
      <div className="flex flex-row items-center justify-between p-10 -mb-10">
        <div className="flex justify-center items-center gap-2">
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

          <select
            className="select border-primary w-full max-w-xs"
            onChange={handleSortChange}
          >
            <option disabled selected>
              Sort by
            </option>
            <option value="album">Album name</option>
            <option value="song">Song name</option>
          </select>
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
