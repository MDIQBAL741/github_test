import { useState, useEffect } from "react";
import "./Reg.css";
import Textbox from "../Components/Textbox";
import Dropdown from "../Components/Dropdown";
// import Sidenav from "../Components/Sidenav";
import ButtonComponent from "../Components/Button";
import axios from "axios";
function Panel() {
  const formData = {
    panelName: "",
    // panelId: "",
    panelEmail: "",
    // timeSlot: "",
    // date: "",
    numberOfSlots: "",
    startTime: "",
    endTime: "",
    // panelType: { value: "", description: "Select" },
    skill: { value: "", description: "Select" },
  };
  const [name, setName] = useState("");
  const [panelForm, setPanelForm] = useState({ FormData });
  const[skill,setSkill]=useState("");
  const [values,setvalues]=useState([])
  const updateForm = (keyName, value) => {
    setPanelForm((prevState) => {
      const newState = { ...prevState, [keyName]: value };
      return newState;
    });
  };
  const clearForm = () => {
    setPanelForm(formData);
    setSkill("");
  };
  const handleForm = (e) => {
    e.preventDefault();
    const formData = {
      panelName: panelForm.panelName,
      skill: skill,
      panelEmail: panelForm.panelEmail,
      numberOfSlots: panelForm.numberOfSlots,
      startTime: panelForm.startTime,
      endTime: panelForm.endTime,
      // "level": panelForm.label.value
    };
    axios
      .post("http://localhost:8083/addpanel", formData)
      .then((dat) => console.log(formData))
      .catch((err) => console.log(err));
  };
 
  useEffect(() => {
    console.log("useEffect", panelForm);
  }, [panelForm]);
  const getskills=()=>{
    axios
    .get("http://localhost:8083/getallskills")
    .then((skill)=>{
      setvalues(skill.data);
      clearForm();
    })
    .catch((err) => console.log(err));
  };
 
  const skillvalue =(e)=>{
   
    const selectedvalue=e.target.value;
    console.log(selectedvalue,"skill");
    setSkill(e.target.value);
  }
  useEffect(()=>{
    getskills();
 
  },[]);
  return (
    <div class="main">
      <form class="form" onSubmit={handleForm}>
        <header class="header">
          <h4>Panel Services</h4>
        </header>
        <div class="formd">
          <div class="names">
            <div class="d">
              <Dropdown
                label={"Panel Type"}
                handleChange={(value) => updateForm("panelType", value)}
                val={panelForm?.panelType?.value}
                data={[
                  { label: "Internal", value: 1 },
                  { label: "External", value: 2 },
                ]}
              />
            </div>
            <div>
              <Textbox
                label="Panel Name"
                placeholder="Enter Panel Name"
                handleChange={(value) => updateForm("panelName", value)}
                value={panelForm.panelName}
                format={"text"}
                maxlength={"30"}
              />
            </div>
            <div>
              <Textbox
                label="Panel-ID"
                placeholder="Enter Panel Id"
                handleChange={(value) => updateForm("panelId", value)}
                value={panelForm.panelId}
                format={"text"}
                maxlength={"10"}
                pattern={"^[0-9]{2}-[0-9]{3}-[0-9]{4}"}
              />
            </div>
    
          </div>
          <div class="sec1">
            <div>
              <Textbox
                label="Panel Email"
                placeholder="Enter Panel Email"
                handleChange={(value) => updateForm("panelEmail", value)}
                value={panelForm.panelEmail}
                format={"email"}
                maxlength={"30"}
                pattern={""}
              />
            </div>
     
            <div className="drop1">
                <div className="dd">
              <label>Skill</label>
              <select aria-label="Default select example" class="ddl-style form-select" onChange={skillvalue}>
              <option>select skill</option>
             
              {values.map((opts,i)=><option>{opts.skill}</option>)}
             
              </select>
              </div>
              </div>
         
              <div>
              <Textbox
                label="No.Of Slots"
                placeholder="Enter No. of slots"
                handleChange={(value) => updateForm("numberOfSlots", value)}
                value={panelForm.numberOfSlots}
                format={"text"}
                maxlength={"10"}
                pattern={""}
              />
            </div>
          </div>
          <div class="sec2">
           
           
            <div>
              <Textbox
                label="Start Time"
                placeholder="Enter Start Time"
                handleChange={(value) => updateForm("startTime", value)}
                value={panelForm.startTime}
                format={"time"}
                maxlength={"10"}
                pattern={""}
              />
            </div>
            <div>
            <div class="names1">
              <Textbox
                label="End Time"
                placeholder="Enter End Time"
                handleChange={(value) => updateForm("endTime", value)}
                value={panelForm.endTime}
                format={"time"}
                maxlength={4}
              />
            </div>
          </div>
          </div>
         
         
          <div class="button">
            <ButtonComponent
              type={"submit"}
              label={"Submit"}
              variant={"light"}
              handleClick={clearForm}
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
export default Panel;