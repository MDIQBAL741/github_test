import React from "react";
import { Button } from "react-bootstrap";
import "./Reg.css";
import { Link } from "react-router-dom";
import { useState, useEffect } from "react";
import axios from "axios";
import { toast } from "react-toastify";
// import Table from "../Components/Tablecom";
function Skills() {
  const [skills, setSkills] = useState("");
  const [Updateskills, putSkills] = useState("");
  const [delskills, deleteSkills] = useState("");
  const [masterData, setMasterData] = useState([]);
  const [updateskill, setupdateskill] = useState(-1);
  const getSkill = () => {
    axios
      .get("http://localhost:8083/getallskills")
      .then((skill) => {
        setMasterData(skill.data);
      })
      .catch((err) => console.log(err));
  };
  useEffect(() => {
    getSkill();
  }, []);
  const skillAdd = (e) => {
    e.preventDefault();
    axios
      .post("http://localhost:8083/addskills", { skill: skills })
      .then((res) => {
        toast.success("skill added sucessfully")
        if (res.status == 200) {
          getSkill();
        }
      });
 
    setSkills("");
  };
  const skillUpdate = (e) => {
    e.preventDefault();
    axios
      .put("http://localhost:8083/updateskill/", { skill: Updateskills })
      .then((res) => {
        if (res.status == 200) {
          getSkill();
        }
      });
      getSkill();
  };
  const skillDelete = (e) => {
    e.preventDefault();
    axios
      .delete("http://localhost:8083/deleteby", { skill: delskills })
      .then((res) => {
        if (res.status == 200) {
          getSkill();
        }
      });
    deleteSkills();
  };
  function handleEdit(skillsId){
    setupdateskill(skillsId)
  }
  function Edit({rec,skills,setSkills}){
    function handleskill(e) {
      const skill = e.target.value;
      const updateskill = masterData.map((d) => d.skillsId === rec.skillsId ? {...d, skill:skill} : d)
      setSkills(updateskill)
    }
    return(
      <tr>
      <td><input type="text" name="skillsId"  value={rec.skillsId}/></td>
      <td><input type="text" name="skill" onChange={handleskill} value={rec.skill}/></td>
      <td><button type="submit">Update</button></td>
      </tr>
    )
  }
  function handleUpdate(e){
    e.preventDefault()
    setupdateskill(-1)
  }
  return (
    <div>
      <div className="main">
        <div className="form">
          <header className="header">
            <h4>Skill Services</h4>
          </header>
          <div className="formd">
            <div className="addskills">
              <div>
                <input
                  className="box"
                  placeholder="    enter to add new skill"
                  onChange={(e) => setSkills(e.target.value)}
                  value={skills}
                  type={"text"}
                />
              </div>
              <div>
                <Link to="/create">
                  <Button
                    type="submit"
                    variant={"light"}
                    className="btn btn-primary1"
                    data-bs-toggle="modal"
                    data-bs-target="#exampleModal"
                    onClick={skillAdd}
                  >
                    Add Skill
                  </Button>
                </Link>
              </div>
            </div>
            <div>
              <form onSubmit={handleUpdate}>
              <table className="table">
                <thead>
                  <tr>
                    <th>Skill Id</th>
                    <th>Skill Name</th>
                    <th>Action</th>
                  </tr>
                </thead>
                <tbody>
              
                  {masterData.map((rec) => {
                    return (
                      updateskill === rec.skillsId ? <Edit rec={rec} skills={skills} setSkills={setSkills}/>:
                      <tr>
                        <td>{rec.skillsId}</td>
                        <td>{rec.skill}</td>
                        <td className="updatedelete">
                          <Button className="btn btn-sm btn-success" onClick={()=> handleEdit(rec.skillsId)} type="button">
                            Update
                          </Button>
 
                          <Link to="/delete">
                            <Button
                              type="submit"
                              variant={"light"}
                              className="btn btn-primary3"
                              data-bs-toggle="modal"
                              data-bs-target="#exampleModal"
                              onClick={skillDelete}
                            >
                              Delete Skill
                            </Button>
                          </Link>
                        </td>
                      </tr>
                    );
                  })}
                </tbody>
              </table>
 
              <div>
                <nav aria-label="Page navigation example">
                  <ul class="pagination justify-content-center">
                    <li class="page-item disabled">
                      <a class="page-link" href="#" tabindex="-1">
                        Previous
                      </a>
                    </li>
 
                    <li class="page-item">
                      <a class="page-link" href="#">
                        1
                      </a>
                    </li>
 
                    <li class="page-item">
                      <a class="page-link" href="#">
                        2
                      </a>
                    </li>
 
                    <li class="page-item">
                      <a class="page-link" href="#">
                        3
                      </a>
                    </li>
 
                    <li class="page-item">
                      <a class="page-link" href="#">
                        Next
                      </a>
                    </li>
                  </ul>
                </nav>
 
                <div
                  className="modal fade"
                  id="exampleModal"
                  tabindex="-1"
                  aria-labelledby="exampleModalLabel"
                  aria-hidden="true"
                >
                  <div className="modal-dialog">
                    <div className="modal-content">
                      <div className="modal-header">
                        <h1 className="modal-title fs-5" id="exampleModalLabel">
                          Do You Want to continue?
                        </h1>
 
                        <button
                          type="button"
                          className="btn-close"
                          data-bs-dismiss="modal"
                          aria-label="Close"
                        ></button>
                      </div>
 
                      <div className="modal-body"> </div>
 
                      <div className="modal-footer">
                        <button
                          type="button"
                          className="btn btn-secondary"
                          data-bs-dismiss="modal"
                        >
                          Cancel
                        </button>
 
                        <button type="button" className="btn btn-primary1">
                          Save changes
                        </button>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              </form>
            </div>
          </div>
        </div>
      </div>
 
      <div>{/* <Table /> */}</div>
    </div>
  );
}
 
export default Skills;