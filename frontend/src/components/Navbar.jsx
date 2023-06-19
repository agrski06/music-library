import React from "react";
import { Link, NavLink } from "react-router-dom";

function Navbar() {

  return (
    <div className="navbar bg-neutral flex justify-between">
      <NavLink
        to={"/"}
        className="btn btn-ghost animate-none normal-case text-xl"
      >
        Music Library
      </NavLink>
      <div className="flex gap-8 mr-12 text-xl">
        <NavLink to={"/"} className="btn-ghost">Songs</NavLink>
        <NavLink to={"/albums"} className="btn-ghost">Albums</NavLink>
        <NavLink to={"/artists"} className="btn-ghost">Artists</NavLink>
        <NavLink to={"/metrics"} className="btn-ghost">Metrics</NavLink>
      </div>
    </div>
  );
}

export default Navbar;
