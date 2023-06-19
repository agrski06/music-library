import React, { useEffect, useState } from 'react'
import Navbar from './Navbar'
import axios from 'axios';
import { NavLink } from 'react-router-dom';

export const Artists = () => {
  const [artists, setArtists] = useState([]);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get("http://localhost:8080/api/artists");
        setArtists(response.data);
        setIsLoading(false);
        console.log(response.data)
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
        <h1 className="text-3xl">All artists: </h1>
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
        {artists.map((artist, index) => {
          return (
              <div className="text-xl font-medium">
                {index+1}. {artist.name}
              </div>
          );
        })}
      </div>
    </div>
  )
}
