import React, { useEffect, useState } from "react";
import Navbar from "./Navbar";
import axios from "axios";

const Metrics = () => {
  const [byPath, setByPath] = useState({});
  const [byMethod, setByMethod] = useState({});
  const [byBoth, setByBoth] = useState({});
  const [total, setTotal] = useState(0);

  useEffect(() => {
    const fetchData = async () => {
      try {
        let response = await axios.get(
          "http://localhost:8080/api/metrics/paths"
        );
        setByPath(response.data);

        response = await axios.get("http://localhost:8080/api/metrics/methods");
        setByMethod(response.data);

        response = await axios.get("http://localhost:8080/api/metrics/both");
        setByBoth(response.data);

        response = await axios.get("http://localhost:8080/api/metrics/total");
        setTotal(response.data);
      } catch (error) {
        console.log(error);
      }
    };

    fetchData();
  }, []);

  return (
    <div>
      <Navbar />
      <div className="flex items-center flex-col">
        <h1 className="text-2xl p-4">TOTAL: {total}</h1>
        <div className="flex w-full p-20 justify-between -mt-10">
          <pre>{`${JSON.stringify(byBoth, null, 2)}`}</pre>
          <pre>{`${JSON.stringify(byPath, null, 2)}`}</pre>
          <pre>{`${JSON.stringify(byMethod, null, 2)}`}</pre>
        </div>
      </div>
    </div>
  );
};

export default Metrics;
