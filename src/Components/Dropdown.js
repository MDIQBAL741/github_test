import Form from "react-bootstrap/Form";
import { useState } from "react";
const Dropdown = ({ label, data,dataa,handleChange,val,errorMessage,isError ,value}) => {
  const [errorFlag, setErrorFlag] = useState(isError);
  const [errorMsg, setErrorMsg] = useState(errorMessage);
  const validateField = () => {
    if (value === "" || !value) {
      setErrorFlag(true);
      setErrorMsg(label + " should not be empty");
    }
    setErrorFlag(false);
    setErrorMsg("");
  };
  return (
    <div class="dd">
      <label for={label}>{label}</label>
      <Form.Select aria-label="Default select example" className="ddl-style"
        onChange={(e)=>handleChange({value:e.target.value,description:e.target.label})}
        onBlur={validateField}>
        <option value={""}>Select</option>
        {data.map((item, index) => (
          <option key={index + 1} value={item.value} selected={val===item.value}>
            {item.label}
          </option>
        ))}
      </Form.Select>
      
    </div>
  );
};
export default Dropdown;
