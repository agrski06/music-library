import React, { useEffect, useState } from "react";
import Navbar from "./Navbar";
import axios from "axios";

export const AddSong = () => {
  const [artists, setArtists] = useState([]);
  const [album, setAlbum] = useState({ id: 0, name: "" });
  const [albums, setAlbums] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [errorMessage, setErrorMessage] = useState("");
  const [newSong, setNewSong] = useState({
    name: "",
    duration: "",
    artists: artists,
    album: album,
  });

  useEffect(() => {
    const fetchData = async () => {
      try {
        let response = await axios.get("http://localhost:8080/api/artists");
        setArtists(response.data);

        response = await axios.get("http://localhost:8080/api/albums");
        setAlbums(response.data);

        setIsLoading(false);
      } catch (error) {
        console.log(error);
      }
    };

    fetchData();
  }, []);

  const handleSubmit = async () => {
    try {
      const response = await axios.post(
        "http://localhost:8080/api/songs",
        newSong
      );
      window.location.href = "/";
    } catch (error) {
      console.log(error);
      setErrorMessage(error.response.data.map(msg => <p>{msg}</p>));
    }
  };

  const handleSongChange = ({ currentTarget: input }) => {
    setNewSong({ ...newSong, [input.name]: input.value });
  };

  const handleArtistChange = (event) => {
    if (
      newSong.artists.find((artist) => artist.id === +event.target.value) !== undefined
    ) {
      return;
    }

    const temp = artists.find(a => a.id === +event.target.value)

    setNewSong((prevState) => ({
      ...prevState,
      artists: [...prevState.artists, { id: +event.target.value, name: temp.name }],
    }));
    console.log(newSong);
  };

  const handleAlbumChange = (event) => {
    setNewSong({ ...newSong, album: { id: +event.target.value } });
    console.log(newSong);
  };

  return (
    <div>
      <Navbar />
      <div className="flex flex-col items-center gap-4 justify-center p-10">
        <h1 className="text-2xl">Add song</h1>
        <div className="form-control w-full max-w-xs">
          <label className="label">
            <span className="label-text"></span>
          </label>
          <input
            type="text"
            placeholder="Name"
            className="input input-bordered input-accent w-full max-w-xs"
            name="name"
            onChange={handleSongChange}
          />
        </div>

        <div className="form-control w-full max-w-xs">
          <label className="label">
            <span className="label-text"></span>
          </label>
          <input
            type="text"
            placeholder="Duration"
            className="input input-bordered input-accent w-full max-w-xs"
            name="duration"
            onChange={handleSongChange}
          />
        </div>

        <select
          className="select border-accent w-full max-w-xs"
          defaultValue={"s"}
          onChange={handleArtistChange}
        >
          <option disabled selected>
            Choose artist
          </option>
          {artists.map((artist) => {
            return (
              <option value={artist.id} key={artist.id}>
                {artist.name}
              </option>
            );
          })}
        </select>
        <p>
          Selected artists: {newSong.artists.map((artist) => {
            return <p> {artist.name} </p>;
          })}
        </p>

        <select
          className="select border-accent w-full max-w-xs mt-4"
          onChange={handleAlbumChange}
          defaultValue={"s"}
        >
          <option disabled selected>
            Choose album
          </option>
          {albums.map((album) => {
            return (
              <option value={album.id} key={album.id}>
                {album.name}
              </option>
            );
          })}
        </select>

        <button className="btn btn-primary" onClick={handleSubmit}>
          Save
        </button>

        {<div className={errorMessage ? `visible` : `invisible`}>
          {errorMessage}
        </div>}
      </div>
    </div>
  );
};
