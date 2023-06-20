import React, { useState } from "react";
import Navbar from "./Navbar";
import axios from "axios";

export const AddArtist = () => {
  const [newArtist, setNewArtist] = useState({ name: "" });
  const [errorMessage, setErrorMessage] = useState("");

  const handleChange = ({ currentTarget: input }) => {
    setNewArtist({ ...newArtist, [input.name]: input.value });
  };

  const handleSubmit = async () => {
    try {
      const response = await axios.post(
        "http://localhost:8080/api/artists",
        newArtist
      );
      window.location.href = "/";
    } catch (error) {
      setErrorMessage(error.response.data.map(msg => msg));
    }
  };

  return (
    <div>
      <Navbar />
      <div className="flex flex-col items-center gap-4 justify-center p-10">
        <h1 className="text-2xl">Add artist</h1>
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
