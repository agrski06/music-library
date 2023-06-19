import React from "react";
import { Link } from "react-router-dom";

export const AlbumListItem = ({ id, name, releaseYear, songs }) => {
  const handleDelete = () => {};
  return (
    
    <div className="mb-6 flex w-full gap-2 items-center">
      <div className="flex w-full cursor-pointer rounded-md border-2 border-primary bg-base-100 p-6 shadow-lg transition hover:bg-neutral-focus ">
        <div className="flex flex-row items-center justify-between w-full">
          <div className="flex flex-row gap-8 items-center w-full justify-between">
            <div className="flex flex-row gap-8 items-center">
              <div className="text-xl text-gray-200 italic">{name}</div>
              <span>released: </span>
              <div className="text-xl text-gray-200 italic">{releaseYear}</div>
            </div>
          </div>
        </div>
      </div>



      <button
        className="btn outline-error h-20 bg-base-100 text-white btn-error"
        onClick={handleDelete}
      >
        Delete
      </button>
    </div>
    
  );
};
