import React from "react";
import Navbar from "./Navbar";
import SongList from "./SongList";

function Home() {
  return (
    <div className="h-screen w-full bg-base-100">
      <Navbar />
      <div className="flex flex-row items-center justify-between p-10 -mb-10">
        <h1 className="text-3xl">All songs: </h1>
        <div className="flex flex-row justify-end">
          <button className="btn btn-outline btn-primary">Primary</button>
        </div>
      </div>
      <div className="p-10">
        <SongList />
      </div>
    </div>
  );
}

export default Home;
