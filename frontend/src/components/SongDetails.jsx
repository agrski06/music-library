import React, { useEffect, useState } from "react";
import Navbar from "./Navbar";
import axios from "axios";
import { useParams } from "react-router-dom";

export const SongDetails = () => {
  const routeParams = useParams();
  const [songs, setSongs] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [updatedSong, setUpdatedSong] = useState({});

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8080/api/songs/${routeParams.id}`
        );
        setSongs(response.data);
        setUpdatedSong(response.data);
        setIsLoading(false);
      } catch (error) {
        console.log(error);
      }
    };

    fetchData();
  }, []);

  const handleChange = ({ currentTarget: input }) => {
    setUpdatedSong({ ...updatedSong, [input.name]: input.value });
  };

  const handleSubmit = async () => {
    try {
      const response = await axios.put(
        "http://localhost:8080/api/songs",
        updatedSong,
      );
      window.location.href = '/'
    } catch (error) {
      console.log(error);
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

        <button className="btn btn-primary" onClick={handleSubmit}>Edytuj</button>
      </div>
    </div>
  );
};
