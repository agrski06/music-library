import React, { useEffect, useState } from "react";
import Navbar from "./Navbar";
import axios from "axios";
import { useParams } from "react-router-dom";

export const SongDetails = () => {
  const routeParams = useParams();
  const [artists, setArtists] = useState([]);
  const [songs, setSongs] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [errorMessage, setErrorMessage] = useState("");
  const [updatedSong, setUpdatedSong] = useState({
    name: "",
    duration: "",
    artists: [],
    album: {},
  });

  useEffect(() => {
    const fetchData = async () => {
      try {
        let response = await axios.get(
          `http://localhost:8080/api/songs/${routeParams.id}`
        );
        setSongs(response.data);
        setUpdatedSong(response.data);

        response = await axios.get("http://localhost:8080/api/artists");
        setArtists(response.data);

        setIsLoading(false);
      } catch (error) {
        console.log(error);
      }
    };

    fetchData();
  }, []);

  const handleChange = ({ currentTarget: input }) => {
    setErrorMessage("")
    setUpdatedSong({ ...updatedSong, [input.name]: input.value });
  };

  const handleSubmit = async () => {
    try {
      const response = await axios.put(
        "http://localhost:8080/api/songs",
        updatedSong
      );
      window.location.href = "/";
    } catch (error) {
      console.log(error.response.data.message);
      setErrorMessage(error.response.data.message)
    }
  };

  const handleArtistChange = (event) => {
    if (
      updatedSong.artists.find(
        (artist) => artist.id === +event.target.value
      ) !== undefined
    ) {
      return;
    }

    const temp = artists.find((a) => a.id === +event.target.value);

    setUpdatedSong((prevState) => ({
      ...prevState,
      artists: [
        ...prevState.artists,
        { id: +event.target.value, name: temp.name },
      ],
    }));
  };

  const handleDeleteArtist = (e) => {
    const objWithIdIndex = updatedSong.artists.findIndex(
      (obj) => obj.id === +e.target.value
    );
    let newArr;
    if (objWithIdIndex > -1) {
      console.log(updatedSong.artists);
      newArr = [...updatedSong.artists];
      newArr.splice(objWithIdIndex, 1);
      console.log(updatedSong.artists);
      setUpdatedSong((prevState) => ({
        ...prevState,
        artists: newArr,
      }));
    }
  };

  return (
    <div>
      <Navbar />
      <div className="flex flex-col items-center gap-4 justify-center p-10">
        <div className="form-control w-full max-w-xs">
          <label className="label">
            <span className="label-text">Current name: {songs.name}</span>
          </label>
          <input
            type="text"
            placeholder="Type here"
            className="input input-bordered input-accent w-full max-w-xs"
            name="name"
            onChange={handleChange}
          />
        </div>

        <div className="form-control w-full max-w-xs">
          <label className="label">
            <span className="label-text">Current name: {songs.duration}</span>
          </label>
          <input
            type="text"
            placeholder="Type here"
            className="input input-bordered input-accent w-full max-w-xs"
            name="duration"
            onChange={handleChange}
          />
        </div>

        <select
          className="select border-accent w-full max-w-xs mt-4"
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
        <div>
          Current artists:{" "}
          {updatedSong.artists.map((artist) => {
            return (
              <div className="flex gap-2 p-1" key={artist.id}>
                {artist.id}, {artist.name}
                <button
                  className="btn btn-xs btn-error"
                  value={artist.id}
                  onClick={handleDeleteArtist}
                >
                  Delete
                </button>
              </div>
            );
          })}
        </div>

        <button className="btn btn-primary" onClick={handleSubmit}>
          Edit
        </button>

        {<div className={errorMessage ? `visible` : `invisible`}>
          {errorMessage}
        </div>}

      </div>
    </div>
  );
};
