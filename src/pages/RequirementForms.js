import { useState, useEffect } from "react";

 

import "./Reg.css";

import Textbox from "../Components/Textbox";

import Dropdown from "../Components/Dropdown";

import Sidenav from "../Components/Sidenav";

import ButtonComponent from "../Components/Button";
import axios from "axios";
import { toast } from "react-toastify";

function Requirement() {

  const formData = {

    accountName: "",

    projectName: "",

    managerName: "",

    totalPosition: "",

    skill: { value: "", description: "Select" },

    projectLocation: { value: "", description: "Select" },

  };

  const [name, setName] = useState("");

  const [requirementForm, setRequirementForm] = useState(formData);

  const updateForm = (keyName, value) => {

    setRequirementForm((prevState) => {

      const newState = { ...prevState, [keyName]: value };

      return newState;

    });

  };

  const clearForm = () => {

    setRequirementForm(formData);

  };
  const handleForm =(e) =>{
    e.preventDefault();
    const formData ={
        accountName: requirementForm.accountName,
        projectName: requirementForm.projectName,
        managerName: requirementForm.managerName,
        totalPosition: requirementForm.totalPosition,
        projectLocation: requirementForm.projectLocation.value,
        skill: requirementForm.skill.value,
    };
    axios.post("http://localhost:8083/requirement/add",formData)
    .then((dat) => {
      toast.success("Requirement Register sucessfully")
    })
    .catch((err) => console.log(err));
    clearForm();
  }

  useEffect(() => {

  }, [requirementForm]);

 

  return (

    <div class="main">

      {/* <aside>

        <Sidenav />

      </aside> */}

      <form class="form" onSubmit={handleForm}>

        <header class="header">

          <h4>Requirement Form</h4>

        </header>

        <div class="formd">

          <div class="names">

            <Textbox

              label="Requirement ID"

              placeholder="Enter Name"

              handleChange={(value) => updateForm("reqId", value)}

              value={requirementForm.reqId}

              format={"text"}

              maxlength={"30"}

            />

            <div>

              <Textbox

                label="Account Name"

                placeholder="Enter Account Name"

                handleChange={(value) => updateForm("accountName", value)}

                value={requirementForm.accountName}

                format={"text"}

                maxlength={"30"}

              />

            </div>

            <div>

              <Textbox

                label="Project Name"

                placeholder="Enter Project Name"

                handleChange={(value) => updateForm("projectName", value)}

                value={requirementForm.projectName}

                type={"alpha"}

              />

            </div>

          </div>

          <div class="sec1">

            <div>

              <Textbox

                label="Manager Name"

                placeholder="Enter Manager Name "

                handleChange={(value) => updateForm("managerName", value)}

                value={requirementForm.managerName}

                format={"text"}

                maxlength={10}

              />

            </div>

            <div>

              <Textbox

                label="Total position"

                placeholder="Enter Total postions"

                handleChange={(value) => updateForm("totalPosition", value)}

                value={requirementForm.totalPosition}

                format={"text"}

                maxlength={10}

              />

            </div>

            <div class="d">

              <Dropdown

                label={"Skills/Domain"}

                handleChange={(value) => updateForm("skill", value)}

                val={requirementForm?.skill?.value}

                data={[

                  { label: "Ab initio", value: "Ab initio" },

                  { label: "devops", value: "devops" },

                  { label: "Data Architect", value: "Data Architect" },

                  { label: "Java mid", value: "Java mid" },

                  { label: "Java Senior", value: "Java Senior" },

                  { label: "Java + React", value: "Java + React" },

                  { label: "Main Frame", value: "Main Frame" },

                  { label: "Main Frame + TOSCA", value: "Main Frame + TOSCA" },

                  { label: "QA junior", value: "QA junior" },

                  { label: "QA Performance Testing", value: "QA Performance Testing" },

                  { label: "Scrum master", value: "Scrum master" },

                  { label: "BA", value: "BA" },

                  { label: "Tech BA", value: "Tech BA" },

                ]}

              />

            </div>

          </div>

 

          <div class="sec3">

            <div class="d">

              <Dropdown

                label={"Project Location"}

                handleChange={(value) => updateForm("projectLocation", value)}

                val={requirementForm?.projectLocation?.value}

                data={[

                  { label: "Chennai", value: "Chennai" },

                  { label: "Pune", value: "Pune" },

                ]}

              />

            </div>

          </div>

          <div class="button">

            <ButtonComponent

              type={"submit"}

              label={"Submit"}

              variant={"light"}
              

              // disabled={false}

            />

            <ButtonComponent

              type={"reset"}

              variant={"light"}

              label={"clear"}

              handleClick={clearForm}

            />

          </div>

        </div>

      </form>

    </div>

  );

}

export default Requirement;