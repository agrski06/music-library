import axios from "axios";
import React from "react";
import { Link } from "react-router-dom";

export const SongListItem = ({ id, name, duration, artists, album }) => {
  const handleDelete = async () => {
    try {
      const response = await axios.delete(`http://localhost:8080/api/songs/${id}`);
      window.location.reload();
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <div className="mb-6 flex w-full items-center">
      <div className="flex w-full cursor-pointer rounded-md border-2 border-primary bg-base-100 p-6 shadow-lg transition hover:bg-neutral-focus ">
        <div className="flex flex-row items-center justify-between w-full">
          <Link to={`/song/${id}`} className="w-full">
            <div className="flex flex-row gap-8 items-center">
              <div className="text-xl text-gray-200 italic">{name}</div>
              {artists.map((artist) => {
                return (
                  <div className="text-xl text-gray-200 " key={artist.id}>
                    {artist.name}
                  </div>
                );
              })}
            </div>
          </Link>
          <button className="btn outline-error bg-base-100 text-white btn-error" onClick={handleDelete}>
            Delete
          </button>
        </div>
      </div>
    </div>
  );
};
