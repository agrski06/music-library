import React from "react";
import Navbar from "./Navbar";
import SongList from "./SongList";
import { NavLink } from "react-router-dom";

function Home() {
  return (
    <div className="h-screen w-full bg-base-100">
      <Navbar />
      <div className="flex flex-row items-center justify-between p-10 -mb-10">
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
        <SongList />
      </div>
    </div>
  );
}

export default Home;
