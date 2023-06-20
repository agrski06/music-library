import React, { useState } from "react";
import Navbar from "./Navbar";
import axios from "axios";

export const AddAlbum = () => {
  const [newAlbum, setNewAlbum] = useState({ name: "", releaseYear: 0 });
  const [errorMessage, setErrorMessage] = useState("");

  const handleChange = ({ currentTarget: input }) => {
    setNewAlbum({ ...newAlbum, [input.name]: (input.name === "releaseYear" ? +input.value : input.value) });
  };

  const handleSubmit = async () => {
    try {
      const response = await axios.post(
        "http://localhost:8080/api/albums",
        newAlbum
      );
      console.log(response)
      window.location.href = "/";
    } catch (error) {
      console.log(error);
      setErrorMessage(error.response.data.map(msg => <p>{msg}</p>));
    }
  };

  return (
    <div>
      <Navbar />
      <div className="flex flex-col items-center gap-4 justify-center p-10">
        <h1 className="text-2xl">Add album</h1>
        <div className="form-control w-full max-w-xs">
          <label className="label">
            <span className="label-text">Name:</span>
          </label>
          <input
            type="text"
            placeholder="Name"
            className="input input-bordered input-accent w-full max-w-xs"
            name="name"
            onChange={handleChange}
          />

          <label className="label">
            <span className="label-text">Release year:</span>
          </label>
          <input
            type="number"
            placeholder="Year"
            className="input input-bordered input-accent w-full max-w-xs [appearance:textfield] [&::-webkit-outer-spin-button]:appearance-none [&::-webkit-inner-spin-button]:appearance-none"
            name="releaseYear"
            onChange={handleChange}
            onKeyPress={(event) => {
              if (!/[0-9]/.test(event.key)) {
                event.preventDefault();
              }
            }}
          />
        </div>

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
