import React, { useEffect, useState } from "react";
import Navbar from "./Navbar";
import axios from "axios";
import { NavLink } from "react-router-dom";
import { AlbumListItem } from "./AlbumListItem";

const Albums = () => {
  const [albums, setAlbums] = useState([]);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get("http://localhost:8080/api/albums");
        setAlbums(response.data);
        console.log(response.data);
        setIsLoading(false);
      } catch (error) {
        console.log(error);
      }
    };

    fetchData();
  }, []);

  return (
    <div>
      <Navbar />
      <div className="flex flex-row items-center justify-between p-10 -mb-10">
        <h1 className="text-3xl">All albums: </h1>
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
      <div className="flex flex-col gap-1 p-10">
        {albums.map((album) => {
          return (
            <div className="collapse collapse-arrow rounded-none bg-neutral">
              <input type="radio" name="my-accordion-2" />
              <div className="collapse-title text-xl font-medium">
                {album.name}
              </div>
              <div className="collapse-content">
                {album.songs.map((song, index) => {
                  return <p>{index+1}. {song.name}</p>
                })}
              </div>
            </div>
          );
        })}
      </div>
    </div>
  );
};

export default Albums;
