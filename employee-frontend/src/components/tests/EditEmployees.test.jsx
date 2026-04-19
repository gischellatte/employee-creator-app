import { render, screen } from '@testing-library/react';
import { MemoryRouter, useNavigate, useParams } from 'react-router';
import EditEmployees from '../EditEmployees';
import * as router from  'react-router';
import userEvent from '@testing-library/user-event';
import { expect } from 'vitest';


beforeEach(() => {
 //must spyOnUseParams
 vi.spyOn(router, 'useParams').mockReturnValue({id:"4"});   
 global.fetch = vi.fn(); //only put global.fetch = vi.fn inside beforeEach(). We can put it in every test file that requires a fetch

});


it ("Should update user's details if they wish to edit.", async ()=>{

    const user = userEvent.setup();

    //global.fetch.mockResolvedValueOnce() must be called before fetch. If your component uses fetch, you must use this.
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

 const input = await screen.findByDisplayValue("Kim")//check useEffect, return firstName dari mockEmployee itu Kim

  await user.clear(input);
  await user.type(input, "Park");
  //asserts
  expect(input).toHaveValue("Park"); 

});

it("It should give a confirmation after user clicks on the save button", async ()=> {
    //1. set up userEvent (user simulation)
    const user2 = userEvent.setup();

    //2. Spies alert (monitors window.alert)
    const alertSpy = vi.spyOn(window, 'alert').mockImplementation(() => {});

    //3. (Most Important) Mock Fetch, not a real fetch. A browser fetch is fetch("/api/employees")
    //requires 2 mockResolvedValueOnce because save (it is a PATCH) will not work without GET
    //GET prepares UI, PATCH is connected to user's action
    global.fetch = vi.fn()
    .mockResolvedValueOnce({
    json:() => Promise.resolve({}),  //3.1 First Fetch - loads data
     id: 4,
        firstName: "Kim"
    })
    .mockResolvedValueOnce({
     ok:true,    
    json:() => Promise.resolve({})    //3.2 Save data (PATCH)
   
    });

    //4. renders component
    render(
    <MemoryRouter>
        <EditEmployees/>
    </MemoryRouter>
    );

    //5. Look for the save button
    const saveButtons = screen.getByRole('button', {name: /save/i});
    
    //6. User clicks on the save button
    await user2.click(saveButtons);
    //dont use screen.queryByText for window alert messages, because it is not rendered
    //const alertText = screen.queryByText(/updated/i);

    //7. if (feedbc.ok)  in the component runs 
    //8. asserts alert
    expect(alertSpy).toHaveBeenCalledWith(expect.stringContaining("Successfully updated employee number"));
    //expect(alertText).toBeInTheDocument();
});



