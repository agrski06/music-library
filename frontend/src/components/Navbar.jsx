import React from "react";
import { Link, NavLink } from "react-router-dom";

function Navbar() {
  return (
    <div className="navbar bg-neutral">
      <NavLink to={"/"} className="btn btn-ghost animate-none normal-case text-xl">
          Music Library
      </NavLink>
    </div>
  );
}

export default Navbar;
