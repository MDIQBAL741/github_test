import { useState, useEffect } from "react";

import "./Reg.css";

import Textbox from "../Components/Textbox";

import Dropdown from "../Components/Dropdown";

import ButtonComponent from "../Components/Button";

import axios from "axios";
import { toast } from "react-toastify";
import Textarea from "../Components/Textarea";

function Status() {
  const formData = {
    name: "",

    email: "",

    comment: "",

    status: "",
  };
  const [name, setName] = useState("");
  const [statusupdate, setstatusupdate] = useState(formData);
  const updateForm = (keyName, value) => {
    setstatusupdate((prevState) => {
      let newState = formData;
      newState = { ...prevState, [keyName]: value };
      return newState;
    });
  };

  const clearForm = () => {
    setstatusupdate(formData);
    setName("");
  };

  const handleForm = (e) => {
    e.preventDefault();

    const formData = {
      name: statusupdate.name,

      email: statusupdate.email,

      status: statusupdate.status.value,

      comment: statusupdate.comment,
    };

    axios

      .post("http://localhost:8083/statusupdate", formData)

      .then((dat) => {
        toast.success("Status Update Sucessfully");
      })

      .catch((err) => console.log(err));

    clearForm();
  };

  useEffect(() => {}, [statusupdate]);

  return (
    <div class="main">
      <form class="form" onSubmit={handleForm}>
        <header class="header">
          <h4>Status update</h4>
        </header>

        <div class="formd">
          <div class="names">
            <Textbox
              label="Candidate Name"
              placeholder="Enter Candidate Name"
              handleChange={(value) => updateForm("name", value)}
              value={statusupdate.name}
              type={"alpha"}
              maxlength={"30"}
            />

            <div>
              <Textbox
                label="Candidate Email"
                placeholder="Enter candidate Email"
                handleChange={(value) => updateForm("email", value)}
                value={statusupdate.email}
                format={"email"}
                maxlength={"30"}
                pattern={""}
              />
            </div>

            <div class="status">
              <Dropdown
                label={"status"}
                handleChange={(value) => updateForm("status", value)}
                val={statusupdate?.status?.value}
                data={[
                  { label: "L1-Selected", value: "L1-Selected" },

                  { label: "L2-Selected", value: "L2-Selected" },

                  { label: "L1-Rejected", value: "L1-Rejected" },

                  { label: "Not Join", value: "Not Join" },

                  { label: "cancelled", value: "cancelled" },

                  { label: "Selected", value: "Selected" },

                  { label: "HR-Rejected", value: "HR-Rejected" },

                  { label: "In-Progress", value: "In-Progress" },

                  { label: "Yet to start", value: "Yet to start" },

                  { label: "Hold", value: "Hold" },

                  { label: "Client-Selected", value: "Client-Selected" },

                  { label: "Client-Rejected", value: "Client-Rejected" },

                  { label: "cancelled", value: "cancelled" },
                ]}
              />
            </div>
          </div>

          <div className="comments">
            <Textarea
              label="Comments"
              placeholder="Enter comments here"
              handleChange={(value) => updateForm("comment", value)}
              value={statusupdate.comment}
              rows={5}
              cols={20}
            />
          </div>

          <div class="but1">
            <ButtonComponent
              type={"submit"}
              label={"Submit"}
              variant={"light"}
              handleForm={handleForm}
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

export default Status;
