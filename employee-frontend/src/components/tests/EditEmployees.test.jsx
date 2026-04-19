import { render, screen } from '@testing-library/react';
import { MemoryRouter, useNavigate, useParams } from 'react-router';
import EditEmployees from '../EditEmployees';
import * as router from  'react-router';
import userEvent from '@testing-library/user-event';
import { expect } from 'vitest';


beforeEach(() => {
 vi.spyOn(router, 'useParams').mockReturnValue({id:"4"});   
 global.fetch = vi.fn();
});


it ("Should update user's details if they wish to edit.", async ()=>{

    const user = userEvent.setup();


    global.fetch.mockResolvedValueOnce({
    json: () => Promise.resolve({
        id: 4,
        firstName: "Kim"
     })
    });

    render(
    <MemoryRouter>
        <EditEmployees/>
    </MemoryRouter>
    );

 const input = await screen.findByDisplayValue("Kim");

  await user.clear(input);
  await user.type(input, "Park");
  expect(input).toHaveValue("Park"); 

});

it("It should give a confirmation after user clicks on the save button", async ()=> {

    const user2 = userEvent.setup();

    const alertSpy = vi.spyOn(window, 'alert').mockImplementation(() => {});


    global.fetch = vi.fn()
    .mockResolvedValueOnce({
    json:() => Promise.resolve({}), 
     id: 4,
        firstName: "Kim"
    })
    .mockResolvedValueOnce({
     ok:true,    
    json:() => Promise.resolve({})
    });


    render(
    <MemoryRouter>
        <EditEmployees/>
    </MemoryRouter>
    );


    const saveButtons = screen.getByRole('button', {name: /save/i});

    await user2.click(saveButtons);

    expect(alertSpy).toHaveBeenCalledWith(expect.stringContaining("Successfully updated employee number"));
});



